package com.jfsb.ticketsapp.features.dashboard.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.jfsb.ticketsapp.core.navigation.Routes
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.presentation.viewmodel.TicketsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DashboardScreen(
    ticketsViewModel: TicketsViewModel,
    utils: Utils,
    navController: NavHostController,
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tickets App",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = androidx.compose.material.MaterialTheme.typography.h2.copy(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                },
                contentColor = androidx.compose.material.MaterialTheme.colors.onPrimary,
                elevation = 0.dp,
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Icono de eliminar",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(Routes.Create.route)
                    }) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Icono de nuevo",
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(scrollState)
        ) {
            Tabs(
                pagerState = pagerState,
                coroutineScope = coroutineScope,
                ticketsViewModel = ticketsViewModel,
                navController = navController,
                utils = utils
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Tabs(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    ticketsViewModel: TicketsViewModel,
    navController: NavHostController,
    utils: Utils
) {
    val categoriesTabs = ticketsViewModel.categoriesTabs

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = Color.Black
            )
        },
        backgroundColor = Color.White,
    ) {
        categoriesTabs.forEachIndexed { index, item ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                text = {
                    Text(
                        text = item.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black,
                            fontSize = 12.sp,
                        ),
                    )
                }
            )
        }
    }
    HorizontalPager(
        count = categoriesTabs.size,
        state = pagerState,
    ) {
        TabScreen(
            id = categoriesTabs[pagerState.currentPage].id,
            ticketsViewModel = ticketsViewModel,
            navController = navController,
            utils = utils
        )
    }
}