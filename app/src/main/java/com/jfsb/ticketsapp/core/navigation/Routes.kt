package com.jfsb.ticketsapp.core.navigation

sealed class Routes(val route: String) {
    object Dashboard : Routes("List")
    object Detail : Routes("Detail")
    object Create : Routes("Create")
}