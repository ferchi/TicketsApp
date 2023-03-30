package com.jfsb.ticketsapp.features.dashboard.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import com.jfsb.ticketsapp.features.dashboard.data.datasource.local.TabModel
import javax.inject.Inject
import com.jfsb.ticketsapp.core.network.models.Result
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.domain.usecase.GetTicketsByStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val utils: Utils,
    private val getTicketsByStatusUseCase: GetTicketsByStatusUseCase,
) : ViewModel() {
    val categoriesTabs = listOf(
        TabModel("Nuevos", 1),
        TabModel("En proceso", 2),
        TabModel("Atendidos", 3)
    )

    val utilsAux = utils

    private val _ticketsListState = mutableStateOf<Result<List<TicketModel>>>(Result.Loading)
    val ticketsListState: State<Result<List<TicketModel>>> = _ticketsListState

    fun getTicketsList(status: Int) {
        viewModelScope.launch {
            getTicketsByStatusUseCase.invoke(status).collect { response ->
                _ticketsListState.value = response
            }
        }
    }
}