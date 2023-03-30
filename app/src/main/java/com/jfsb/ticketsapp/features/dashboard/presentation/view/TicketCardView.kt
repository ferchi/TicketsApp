package com.jfsb.ticketsapp.features.dashboard.presentation.view

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jfsb.ticketsapp.core.navigation.Routes
import com.jfsb.ticketsapp.core.utils.Utils
import com.jfsb.ticketsapp.features.dashboard.data.datasource.TicketModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketCardView(
    modifier: Modifier = Modifier,
    ticket: TicketModel,
    utils: Utils,
    onClick: () -> Unit = {},
    deleteOnClick: () -> Unit = {},
    restoreOnClick: () -> Unit = {},
    isFiled: Boolean = false,
    onLongPressed: () -> Unit = {},
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(110.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongPressed() },
                    onTap = { onClick() }
                )
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceEvenly) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = ticket.id!!,
                    style = MaterialTheme.typography.bodySmall
                )
                if (!isFiled) {
                    IconButton(onClick = deleteOnClick, modifier = Modifier.size(24.dp)) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "File",
                            tint = Color.White
                        )
                    }
                }
                else{
                    IconButton(onClick = restoreOnClick, modifier = Modifier.size(24.dp)) {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Restore",
                            tint = Color.White
                        )
                    }
                }
            }
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
        utils = Utils(),
        navController = rememberNavController()
    )
}