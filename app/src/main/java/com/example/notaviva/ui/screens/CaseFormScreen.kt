package com.example.notaviva.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notaviva.data.CaseStatus
import com.example.notaviva.util.CaseUtils
import com.example.notaviva.viewmodel.CaseViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Pantalla única para crear o editar un caso.
 * Si [caseId] es null se trata de una creación; si no, se cargan y actualizan
 * los datos del caso existente.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaseFormScreen(
    viewModel: CaseViewModel,
    caseId: Long?,
    onBack: () -> Unit,
    onSaved: (Long) -> Unit
) {
    val nullFlowState = remember { mutableStateOf<com.example.notaviva.data.CaseEntity?>(null) }
    val existingCase by if (caseId != null) viewModel.getCaseFlow(caseId).collectAsState() else nullFlowState

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(defaultDate()) }
    var status by remember { mutableStateOf(CaseStatus.EN_INVESTIGACION) }
    var statusMenuExpanded by remember { mutableStateOf(false) }
    var initialized by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    // Cuando llega el caso existente (modo edición), precargamos los campos una sola vez.
    LaunchedEffect(existingCase) {
        val case = existingCase
        if (case != null && !initialized) {
            title = case.title
            description = case.description
            date = case.date
            status = CaseStatus.fromName(case.status)
            initialized = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (caseId == null) "Nuevo caso" else "Editar caso") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(20.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError && title.isBlank()
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                isError = showError && description.isBlank()
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Fecha (dd/MM/yyyy)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError && date.isBlank()
            )
            Spacer(Modifier.height(12.dp))

            ExposedDropdownMenuBox(
                expanded = statusMenuExpanded,
                onExpandedChange = { statusMenuExpanded = !statusMenuExpanded }
            ) {
                OutlinedTextField(
                    value = status.label,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Estado") },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = statusMenuExpanded) }
                )
                ExposedDropdownMenu(
                    expanded = statusMenuExpanded,
                    onDismissRequest = { statusMenuExpanded = false }
                ) {
                    CaseStatus.values().forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.label) },
                            onClick = {
                                status = option
                                statusMenuExpanded = false
                            }
                        )
                    }
                }
            }

            if (showError && !CaseUtils.isValidCase(title, description, date)) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Completa título, descripción y fecha antes de guardar.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    if (!CaseUtils.isValidCase(title, description, date)) {
                        showError = true
                        return@Button
                    }
                    val current = existingCase
                    if (caseId != null && current != null) {
                        viewModel.updateCase(
                            current.copy(
                                title = title,
                                description = description,
                                date = date,
                                status = status.name
                            )
                        )
                        onSaved(caseId)
                    } else {
                        viewModel.createCase(title, description, date, status) { newId ->
                            onSaved(newId)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (caseId == null) "Crear caso" else "Guardar cambios")
            }
        }
    }
}

private fun defaultDate(): String =
    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
