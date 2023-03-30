package com.jfsb.ticketsapp.features.dashboard.domain.usecase

import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import com.jfsb.ticketsapp.features.dashboard.domain.repository.TicketRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class UpdateTicketUseCase @OptIn(ExperimentalCoroutinesApi::class)
@Inject constructor(private val repo: TicketRepositoryImpl) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(ticket: TicketModel) = repo.updateTicket(ticket)
}