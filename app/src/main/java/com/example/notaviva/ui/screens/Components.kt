package com.example.notaviva.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notaviva.data.CaseStatus
import com.example.notaviva.ui.theme.StatusCerrado
import com.example.notaviva.ui.theme.StatusInvestigacion
import com.example.notaviva.ui.theme.StatusPublicado

fun colorForStatus(status: CaseStatus): Color = when (status) {
    CaseStatus.EN_INVESTIGACION -> StatusInvestigacion
    CaseStatus.PUBLICADO -> StatusPublicado
    CaseStatus.CERRADO -> StatusCerrado
}

@Composable
fun StatusChip(status: CaseStatus, modifier: Modifier = Modifier) {
    Text(
        text = status.label,
        color = Color.White,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier
            .background(colorForStatus(status), RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}

@Composable
fun ConfirmDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Eliminar") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
