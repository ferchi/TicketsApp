package com.jfsb.ticketsapp.core.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jfsb.ticketsapp.core.utils.Constants.TICKETS
import com.jfsb.ticketsapp.core.utils.Constants.USERS
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.data.repository.TicketRepository
import com.jfsb.ticketsapp.features.dashboard.domain.repository.TicketRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun provideTicketsRef() = Firebase.firestore.collection(TICKETS)

//    @Provides
//    fun provideUsersRef() = Firebase.firestore.collection(USERS)

    @Provides
    fun provideTicketRepository(
        ticketsRef: CollectionReference
    ): TicketRepository = TicketRepositoryImpl(ticketsRef, utils = Utils())
}