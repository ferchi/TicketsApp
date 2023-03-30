package com.jfsb.ticketsapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.presentation.screen.CreateTicketScreen
import com.jfsb.ticketsapp.features.dashboard.presentation.screen.DashboardScreen
import com.jfsb.ticketsapp.features.dashboard.presentation.screen.FiledTicketsScreen
import com.jfsb.ticketsapp.features.dashboard.presentation.viewmodel.TicketsViewModel
import com.jfsb.ticketsapp.features.session.presentation.screen.LoginScreen
import com.jfsb.ticketsapp.features.session.presentation.viewmodel.SessionViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    ticketsViewModel: TicketsViewModel,
    sessionViewModel: SessionViewModel,
    utils: Utils
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Login.route
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
        composable(Routes.FormTicket.route) {
            CreateTicketScreen(
                ticketsViewModel = ticketsViewModel,
                utils = utils,
            )
        }
        composable(Routes.Login.route) {
            LoginScreen(
                sessionViewModel = sessionViewModel,
                navController = navHostController
            )
        }
    }
}