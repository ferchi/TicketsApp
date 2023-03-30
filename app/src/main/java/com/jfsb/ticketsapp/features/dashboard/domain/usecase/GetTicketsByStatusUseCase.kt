package com.jfsb.ticketsapp.features.dashboard.domain.usecase

import com.jfsb.ticketsapp.features.dashboard.domain.repository.TicketRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class GetTicketsByStatusUseCase @Inject constructor (private val repo: TicketRepositoryImpl) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(status:Int) = repo.getTicketByStatus(status)
}