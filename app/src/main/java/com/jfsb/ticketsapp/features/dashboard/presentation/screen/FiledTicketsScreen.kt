package com.jfsb.ticketsapp.features.dashboard.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jfsb.ticketsapp.core.navigation.Routes
import com.jfsb.ticketsapp.features.dashboard.presentation.viewmodel.TicketsViewModel
import com.jfsb.ticketsapp.core.network.models.Result
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import com.jfsb.ticketsapp.features.dashboard.presentation.view.DetailsDialog
import com.jfsb.ticketsapp.features.dashboard.presentation.view.FileTicketDialog
import com.jfsb.ticketsapp.features.dashboard.presentation.view.RestoreTicketDialog
import com.jfsb.ticketsapp.features.dashboard.presentation.view.TicketCardView


@Composable
fun FiledTicketsScreen(
    ticketsViewModel: TicketsViewModel,
    utils: Utils,
    navController: NavHostController
) {
    ticketsViewModel.getTicketsList(4)
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val showInfoDialog: Boolean by ticketsViewModel.showInfoDialog.observeAsState(initial = false)
    val showFileDialog: Boolean by ticketsViewModel.showFileDialog.observeAsState(initial = false)
    val actualTicket: TicketModel by ticketsViewModel.actualTicket.observeAsState(initial = TicketModel())


    if (showInfoDialog)
        DetailsDialog(
            ticket = actualTicket,
            utils = utils,
            setShowDialog = {
                ticketsViewModel.setShowInfoDialog(it)
            })
    if(showFileDialog)
    RestoreTicketDialog(
        ticket = actualTicket,
        setShowDialog = {
            ticketsViewModel.setShowFileDialog(it)
        },
        onClick = {
            ticketsViewModel.updateTicket(it)
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "TICKETS ARCHIVADOS",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = ticketsViewModel.ticketsListState.value) {
            is Result.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is Result.Success<*> -> {
                LazyColumn(Modifier.height(screenHeight)) {
                    items((state.data as List<*>).size) { index ->
                        TicketCardView(
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 4.dp
                            ),
                            ticket = (state.data as List<TicketModel>)[index],
                            utils = ticketsViewModel.utilsAux,
                            onClick = {
                                ticketsViewModel.setActualTicket((state.data)[index])
                                ticketsViewModel.setShowInfoDialog(true)
                            },
                            isFiled = true,
                            navController = navController,
                            onLongPressed = {
                                ticketsViewModel.setActualTicket((state.data)[index])
                                navController.navigate(Routes.FormTicket.route)
                            },
                            restoreOnClick = {
                                ticketsViewModel.setActualTicket((state.data)[index])
                                ticketsViewModel.setShowFileDialog(true)
                            },
                        )
                    }
                }
            }
            is Result.Error -> {
                Text(text = "Error: ${state.message}")
            }
        }
    }
}
