package com.jfsb.ticketsapp.features.dashboard.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketCardView(
    modifier: Modifier = Modifier,
    ticket: TicketModel,
    utils: Utils, 
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = ticket.id!!)
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
                Text(text = "Tipo: ${utils.getTypeName(ticket.type!!)}")
                Text(text = "Prioridad: ${utils.getPriorityName(ticket.priority!!)}")
            }
        }
    }
}

@Preview(name = "TicketCardView")
@Composable
private fun PreviewTicketCardView() {
    val ticket = TicketModel(
        id = "BER-1",
        title = "Title",
        type = 1,
        priority = 3
    )
    TicketCardView(
        ticket = ticket,
        utils = Utils()
    )
}