package com.jfsb.ticketsapp.features.dashboard.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jfsb.ticketsapp.features.dashboard.presentation.viewmodel.TicketsViewModel
import com.jfsb.ticketsapp.core.network.models.Result
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import com.jfsb.ticketsapp.features.dashboard.presentation.view.TicketCardView


@Composable
fun TabScreen(
    id: Int,
    ticketsViewModel: TicketsViewModel,
    navController: NavHostController
) {
    ticketsViewModel.getTicketsList(id)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        when (val state = ticketsViewModel.ticketsListState.value) {
            is Result.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is Result.Success<*> -> {
                LazyColumn(Modifier.height(screenWidth)) {
                    items((state.data as List<*>).size) { index ->
                        TicketCardView(
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 4.dp
                            ),
                            ticket = (state.data as List<TicketModel>)[index],
                            utils = ticketsViewModel.utilsAux,
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
