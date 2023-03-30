package com.jfsb.ticketsapp.features.dashboard.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import com.jfsb.ticketsapp.features.dashboard.data.repository.TicketRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.jfsb.ticketsapp.core.network.models.Result
import com.jfsb.ticketsapp.core.utils.Constants.ADDED

@ExperimentalCoroutinesApi
class TicketRepositoryImpl @Inject constructor(private val ticketRef: CollectionReference) :
    TicketRepository {

    override suspend fun getTicketByStatus(status: Int) = callbackFlow {
        val snapshotListener = ticketRef.orderBy(ADDED).whereEqualTo("status", status)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val tickets = snapshot.toObjects(TicketModel::class.java)
                    Result.Success(tickets)
                } else {
                    Result.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun createTicket(ticketAux: TicketModel) = flow {
        try {
            emit(Result.Loading)
            val ticketId = ticketRef.document().id
            val ticket = ticketAux.copy(id = ticketId)
            val addition = ticketRef.document(ticketId).set(ticket).await()
            emit(Result.Success(addition))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun updateTicket(ticket: TicketModel) = flow {
        try {
            emit(Result.Loading)
            val addition = ticketRef.document(ticket.id!!).set(ticket).await()
            emit(Result.Success(addition))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun deleteTicket(ticket: TicketModel) = flow {
        try {
            emit(Result.Loading)
            val deletion = ticketRef.document(ticket.id!!).delete().await()
            emit(Result.Success(deletion))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: e.toString()))
        }
    }
}