package com.jfsb.ticketsapp.features.session.presentation.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.jfsb.ticketsapp.core.navigation.Routes
import com.jfsb.ticketsapp.core.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor() : ViewModel() {
    private val _username: MutableLiveData<String> = MutableLiveData("")
    val username: LiveData<String> = _username

    fun setUsername(username: String) {
        _username.value = username
    }

    fun login(navController: NavHostController, context: Context) {

        PreferencesManager.setUser(context, _username.value ?: "")

        navController.popBackStack()
        navController.navigate(Routes.Dashboard.route)
    }
}