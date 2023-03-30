package com.jfsb.ticketsapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.presentation.screen.CreateTicketScreen
import com.jfsb.ticketsapp.features.dashboard.presentation.screen.DashboardScreen
import com.jfsb.ticketsapp.features.dashboard.presentation.screen.FiledTicketsScreen
import com.jfsb.ticketsapp.features.dashboard.presentation.viewmodel.TicketsViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    ticketsViewModel: TicketsViewModel,
    utils: Utils
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Dashboard.route
    ) {
        composable(Routes.Dashboard.route) {
            DashboardScreen(
                ticketsViewModel = ticketsViewModel,
                navController = navHostController,
                utils = utils
            )
        }
        composable(Routes.FiledTickets.route) {
            FiledTicketsScreen(
                ticketsViewModel = ticketsViewModel,
                utils = utils,
                navController = navHostController
            )
        }
        composable(Routes.Create.route) {
            CreateTicketScreen(
                ticketsViewModel = ticketsViewModel
            )
        }
    }
}