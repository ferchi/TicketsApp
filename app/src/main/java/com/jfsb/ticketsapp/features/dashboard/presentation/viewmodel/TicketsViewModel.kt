package com.jfsb.ticketsapp.features.dashboard.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import com.jfsb.ticketsapp.features.dashboard.data.datasource.local.TabModel
import javax.inject.Inject
import com.jfsb.ticketsapp.core.network.models.Result
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.domain.usecase.CreateTicketUseCase
import com.jfsb.ticketsapp.features.dashboard.domain.usecase.GetTicketsByStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val utils: Utils,
    private val getTicketsByStatusUseCase: GetTicketsByStatusUseCase,
    private val createTicketUseCase: CreateTicketUseCase
) : ViewModel() {
    val categoriesTabs = listOf(
        TabModel("Nuevos", 1),
        TabModel("En proceso", 2),
        TabModel("Atendidos", 3)
    )

    val teamsList = listOf(
        "SOPORTE",
        "DESARROLLO",
        "ATENCIÓN A CLIENTES",
    )

    val priorityList = listOf(
        "LOW",
        "MEDIUM",
        "HIGH",
    )

    val typeList = listOf(
        "BUG",
        "FEATURE"
    )

    val utilsAux = utils

    private val _ticketsListState = mutableStateOf<Result<List<TicketModel>>>(Result.Loading)
    val ticketsListState: State<Result<List<TicketModel>>> = _ticketsListState

    private val _isTicketAddedState = mutableStateOf<Result<Void?>>(Result.Success(null))
    val isTicketAddedState: State<Result<Void?>> = _isTicketAddedState

    private val _selectedTeam : MutableLiveData<Int> = MutableLiveData()
    val selectedTeam: LiveData<Int> = _selectedTeam

    private val _selectedPriority : MutableLiveData<Int> = MutableLiveData()
    val selectedPriority: LiveData<Int> = _selectedPriority

    private val _selectedType : MutableLiveData<Int> = MutableLiveData()
    val selectedType: LiveData<Int> = _selectedType

    private val _title : MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String> = _title

    private val _author : MutableLiveData<String> = MutableLiveData()
    val author: LiveData<String> = _author

    private val _description : MutableLiveData<String> = MutableLiveData()
    val description: LiveData<String> = _description

    private val _version : MutableLiveData<Double> = MutableLiveData()
    val version: LiveData<Double> = _version

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setAuthor(author: String) {
        _author.value = author
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setVersion(version: Double) {
        _version.value = version
    }

    fun setSelectedTeam(position: String) {
        teamsList.indexOf(position).let {
            _selectedTeam.value = it
        }
    }

    fun setSelectedPriority(position: String) {
        priorityList.indexOf(position).let {
            _selectedPriority.value = it
        }
    }

    fun setSelectedType(position: String) {
        typeList.indexOf(position).let {
            _selectedType.value = it
        }
    }

    fun getTicketsList(status: Int) {
        viewModelScope.launch {
            getTicketsByStatusUseCase.invoke(status).collect { response ->
                _ticketsListState.value = response
            }
        }
    }

    fun createTicket(ticket: TicketModel) {
        viewModelScope.launch {
            createTicketUseCase.invoke(ticket).collect { response ->
                _isTicketAddedState.value = response
            }
        }
    }

    fun isValidateForm(): Boolean {
        return (_title.value != null || _title.value != "") && (_author.value != null || _author.value != "") && (_description.value != null || _description.value != "") && (_selectedPriority.value != null || _selectedPriority.value != -1)
    }
}