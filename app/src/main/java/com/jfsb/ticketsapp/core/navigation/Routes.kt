package com.jfsb.ticketsapp.core.navigation

sealed class Routes(val route: String) {
    object Dashboard : Routes("Dashboard")
    object FiledTickets : Routes("FiledTickets")
    object Create : Routes("Create")
}