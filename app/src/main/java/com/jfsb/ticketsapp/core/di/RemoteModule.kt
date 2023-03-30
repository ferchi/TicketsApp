package com.jfsb.ticketsapp.core.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jfsb.ticketsapp.core.utils.Constants.TICKETS
import com.jfsb.ticketsapp.core.utils.Constants.USERS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun provideTicketsRef() = Firebase.firestore.collection(TICKETS)

    @Provides
    fun provideUsersRef() = Firebase.firestore.collection(USERS)

//    @Provides
//    fun provideDepsRepository(
//        depsRef: CollectionReference
//    ): DepartmentRepository = DepartmentRepositoryImpl(depsRef)
}