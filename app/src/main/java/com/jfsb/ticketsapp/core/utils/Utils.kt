package com.jfsb.ticketsapp.core.utils

import javax.inject.Inject

class Utils @Inject constructor() {
    fun getTeamName(team: Int): String {
        return when (team){
            1 -> "SOPORTE"
            2 -> "DESARROLLO"
            3 -> "ATENCION A CLIENTES"
            else -> "DEFAULT"
        }
    }

    fun getTypeName(type: Int): String {
        return when (type){
            1 -> "BUG"
            2 -> "FEATURE"
            else -> "DEFAULT"
        }
    }

    fun getPriorityName(priority: Int): String {
        return when (priority){
            1 -> "LOW"
            2 -> "MEDIUM"
            3 -> "HIGH"
            else -> "DEFAULT"
        }
    }
}