package com.jfsb.ticketsapp.features.dashboard.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsDialog(
    setShowDialog: (Boolean) -> Unit,
    utils: Utils,
    ticket: TicketModel
) {
    val format = SimpleDateFormat("dd/MM/yyyy")
    val formattedDate = ticket.date?.let { format.format(it) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(300.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = ticket.id!!,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = ticket.title!!,
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Tipo: ${utils.getTypeName(ticket.type!!)}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Prioridad: ${utils.getPriorityName(ticket.priority!!)}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Autor: ${ticket.author}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Date: $formattedDate",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Equipo: ${utils.getPriorityName(ticket.team!!)}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Version: ${ticket.version.toString()}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Descripcion: ${ticket.description}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}