package com.jfsb.ticketsapp.features.dashboard.data.repository

import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import kotlinx.coroutines.flow.Flow
import com.jfsb.ticketsapp.core.network.models.Result


interface TicketRepository {
    suspend fun getTicketByStatus(status: Int): Flow<Result<List<TicketModel>>>
    suspend fun createTicket(ticketAux: TicketModel): Flow<Result<Void?>>
    suspend fun updateTicket(ticket: TicketModel): Flow<Result<Void?>>
    suspend fun deleteTicket(ticket: TicketModel): Flow<Result<Void?>>
}