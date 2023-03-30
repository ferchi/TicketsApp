package com.jfsb.ticketsapp.features.dashboard.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import com.jfsb.ticketsapp.features.dashboard.presentation.viewmodel.TicketsViewModel
import java.util.*

@Composable
fun CreateTicketScreen(
    ticketsViewModel: TicketsViewModel,
) {
    val selectedPriority: Int by ticketsViewModel.selectedPriority.observeAsState(0)
    val selectedType: Int by ticketsViewModel.selectedType.observeAsState(0)
    val selectedTeam: Int by ticketsViewModel.selectedTeam.observeAsState(0)

    val title: String by ticketsViewModel.title.observeAsState("")
    val author: String by ticketsViewModel.author.observeAsState("")
    val description: String by ticketsViewModel.description.observeAsState("")
    val version: Double by ticketsViewModel.version.observeAsState(0.0)

    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { ticketsViewModel.setTitle(it) },
            label = { Text("Título*") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = author,
            onValueChange = { ticketsViewModel.setAuthor(it) },
            label = { Text("Autor*") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { ticketsViewModel.setDescription(it) },
            label = { Text("Descripción*") },
            modifier = Modifier.fillMaxWidth()
        )
        CustomDropMenu(
            title = "Gravedad*",
            list = ticketsViewModel.priorityList,
            selectedItem = selectedPriority,
            expanded = expanded,
            onClick = { ticketsViewModel.setSelectedPriority(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomDropMenu(
            title = "Tipo*",
            list = ticketsViewModel.typeList,
            selectedItem = selectedType,
            expanded = expanded,
            onClick = { ticketsViewModel.setSelectedType(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomDropMenu(
            title = "Equipo",
            list = ticketsViewModel.teamsList,
            selectedItem = selectedTeam,
            expanded = expanded,
            onClick = { ticketsViewModel.setSelectedTeam(it) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = version.toString(),
            onValueChange = { ticketsViewModel.setVersion(it.toDoubleOrNull() ?: 0.0) },
            label = { Text("Versión") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (!ticketsViewModel.isValidateForm()) {
                    ticketsViewModel.createTicket(
                        TicketModel(
                            added = Date(),
                            author = author,
                            date = Date(),
                            description = description,
                            priority = selectedPriority,
                            status = 1,
                            team = selectedTeam,
                            title = title,
                            type = selectedType,
                            version = version
                        )
                    )
                    Toast.makeText(
                        context,
                        "Ticket creado correctamente",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Faltan campos por rellenar",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Crear ticket", color = Color.White)
        }
    }
}

@Composable
fun CustomDropMenu(
    title: String,
    list: List<String>,
    selectedItem: Int,
    expanded: Boolean,
    onClick: (String) -> Unit
) {
    var expandedAux by remember { mutableStateOf(expanded) }
    Column {
        Text(
            title,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expandedAux = true })
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .width(100.dp)
        ) {
            Text(
                list[selectedItem],
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expandedAux = true })
                    .background(
                        Color.LightGray
                    )
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expandedAux,
            onDismissRequest = { expandedAux = false }
        ) {
            list.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onClick(option)
                        expandedAux = false
                    }
                ) {
                    Text(text = option)
                }
            }
        }
    }
}