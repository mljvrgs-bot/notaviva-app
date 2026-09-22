package com.example.notaviva.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notaviva.data.CaseStatus
import com.example.notaviva.data.EvidenceEntity
import com.example.notaviva.data.InterviewEntity
import com.example.notaviva.util.CaseUtils
import com.example.notaviva.viewmodel.CaseViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaseDetailScreen(
    viewModel: CaseViewModel,
    caseId: Long,
    onBack: () -> Unit,
    onEdit: (Long) -> Unit,
    onDeleted: () -> Unit
) {
    val case by viewModel.getCaseFlow(caseId).collectAsState()
    val interviews by viewModel.getInterviews(caseId).collectAsState()
    val evidences by viewModel.getEvidences(caseId).collectAsState()

    var tab by remember { mutableStateOf(0) }
    var showDeleteCase by remember { mutableStateOf(false) }
    var showAddInterview by remember { mutableStateOf(false) }
    var showAddEvidence by remember { mutableStateOf(false) }
    var statusMenuExpanded by remember { mutableStateOf(false) }

    // Nuevo: guarda la entrevista que se esta editando (null = no hay dialogo de edicion abierto).
    // Usamos la entrevista completa (no solo el id) para poder precargar sus campos en el formulario.
    var interviewBeingEdited by remember { mutableStateOf<InterviewEntity?>(null) }
    var evidenceBeingEdited by remember { mutableStateOf<EvidenceEntity?>(null) }
    // Nuevo: controla el dialogo de aviso cuando se intenta cerrar sin conclusion.
    var showCloseBlockedDialog by remember { mutableStateOf(false) }

    val currentCase = case
    if (currentCase == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    // Regla de negocio: un caso cerrado no debe permitir editar sus entrevistas.
    // Es una decision de diseno (el enunciado no lo detalla explicitamente),
    // consistente con que el cierre representa el fin de la investigacion.
    val caseIsClosed = !CaseUtils.canModifyCaseContent(CaseStatus.fromName(currentCase.status))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentCase.title, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { onEdit(caseId) }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                    }
                    IconButton(onClick = { showDeleteCase = true }) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(currentCase.date, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(6.dp))
                AssistChip(
                    onClick = { statusMenuExpanded = true },
                    label = { Text(CaseStatus.fromName(currentCase.status).label) }
                )
                DropdownMenu(expanded = statusMenuExpanded, onDismissRequest = { statusMenuExpanded = false }) {
                    CaseStatus.values().forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.label) },
                            onClick = {
                                // Nuevo: antes de aplicar el cambio, se valida con CaseUtils
                                // si esta transicion esta permitida (cerrar exige conclusion).
                                if (CaseUtils.canChangeStatusTo(currentCase, option)) {
                                    viewModel.updateStatus(currentCase, option)
                                } else {
                                    showCloseBlockedDialog = true
                                }
                                statusMenuExpanded = false
                            }
                        )
                    }
                }
            }

            TabRow(selectedTabIndex = tab) {
                Tab(selected = tab == 0, onClick = { tab = 0 }, text = { Text("Resumen") })
                Tab(selected = tab == 1, onClick = { tab = 1 }, text = { Text("Entrevistas") })
                Tab(selected = tab == 2, onClick = { tab = 2 }, text = { Text("Conclusión") })
                Tab(selected = tab == 3, onClick = { tab = 3 }, text = { Text("Evidencias") })
            }

            when (tab) {
                0 -> ResumenTab(currentCase.description)
                1 -> EntrevistasTab(
                    interviews = interviews,
                    readOnly = caseIsClosed,
                    onAdd = { showAddInterview = true },
                    onEdit = { interviewBeingEdited = it },
                    onDelete = { viewModel.deleteInterview(it) }
                )
                2 -> ConclusionTab(
                    initialText = currentCase.conclusion,
                    onSave = { viewModel.updateConclusion(currentCase, it) }
                )
                3 -> EvidenciasTab(
                    evidences = evidences,
                    readOnly = caseIsClosed,
                    onAdd = { showAddEvidence = true },
                    onEdit = { evidenceBeingEdited = it },
                    onDelete = { viewModel.deleteEvidence(it) }
                )
            }
        }
    }

    if (showDeleteCase) {
        ConfirmDialog(
            title = "Eliminar caso",
            message = "¿Seguro que deseas eliminar este caso y toda su información asociada?",
            onConfirm = {
                viewModel.deleteCase(currentCase)
                showDeleteCase = false
                onDeleted()
            },
            onDismiss = { showDeleteCase = false }
        )
    }

    if (showAddInterview) {
        AddInterviewDialog(
            onDismiss = { showAddInterview = false },
            onSave = { name, date, findings ->
                viewModel.addInterview(caseId, name, date, findings)
                showAddInterview = false
            }
        )
    }

    // Nuevo: dialogo de edicion. Solo se muestra si interviewBeingEdited no es null.
    interviewBeingEdited?.let { interview ->
        EditInterviewDialog(
            interview = interview,
            onDismiss = { interviewBeingEdited = null },
            onSave = { updated ->
                viewModel.updateInterview(updated)
                interviewBeingEdited = null
            }
        )
    }

    if (showAddEvidence) {
        AddEvidenceDialog(
            onDismiss = { showAddEvidence = false },
            onSave = { name, desc ->
                viewModel.addEvidence(caseId, name, desc)
                showAddEvidence = false
            }
        )
    }

    // Nuevo: dialogo de edicion de evidencia, mismo patron que interviewBeingEdited.
    evidenceBeingEdited?.let { evidence ->
        EditEvidenceDialog(
            evidence = evidence,
            onDismiss = { evidenceBeingEdited = null },
            onSave = { updated ->
                viewModel.updateEvidence(updated)
                evidenceBeingEdited = null
            }
        )
    }

    // Nuevo: aviso cuando se intenta cerrar un caso sin conclusion escrita.
    if (showCloseBlockedDialog) {
        AlertDialog(
            onDismissRequest = { showCloseBlockedDialog = false },
            title = { Text("No se puede cerrar el caso") },
            text = { Text("Para marcar este caso como Cerrado primero debes escribir una conclusión en la pestaña Conclusión.") },
            confirmButton = {
                TextButton(onClick = { showCloseBlockedDialog = false }) { Text("Entendido") }
            }
        )
    }
}

@Composable
private fun ResumenTab(description: String) {
    Column(Modifier.padding(16.dp)) {
        Text("Descripción", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text(description.ifBlank { "Sin descripción registrada." })
    }
}

@Composable
private fun EntrevistasTab(
    interviews: List<InterviewEntity>,
    readOnly: Boolean,
    onAdd: () -> Unit,
    onEdit: (InterviewEntity) -> Unit,
    onDelete: (InterviewEntity) -> Unit
) {
    Column(Modifier.padding(16.dp).fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text("Entrevistas (${interviews.size})", fontWeight = FontWeight.Bold)
            // Si el caso esta cerrado no se permite agregar nuevas entrevistas.
            if (!readOnly) {
                TextButton(onClick = onAdd) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(Modifier.width(4.dp))
                    Text("Nueva entrevista")
                }
            }
        }
        if (readOnly) {
            Spacer(Modifier.height(4.dp))
            Text(
                "Este caso está cerrado: las entrevistas no se pueden modificar.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(Modifier.height(8.dp))
        if (interviews.isEmpty()) {
            Text("Aún no hay entrevistas registradas para este caso.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(interviews, key = { it.id }) { interview ->
                    Card(shape = RoundedCornerShape(10.dp)) {
                        Row(Modifier.padding(12.dp), verticalAlignment = androidx.compose.ui.Alignment.Top) {
                            Column(Modifier.weight(1f)) {
                                Text(interview.personName, fontWeight = FontWeight.Bold)
                                Text(interview.date, style = MaterialTheme.typography.bodyMedium)
                                Spacer(Modifier.height(4.dp))
                                Text("Hallazgos: ${interview.findings}")
                            }
                            // Los botones de editar/eliminar solo aparecen si el caso no esta cerrado.
                            if (!readOnly) {
                                IconButton(onClick = { onEdit(interview) }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                                }
                                IconButton(onClick = { onDelete(interview) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ConclusionTab(initialText: String, onSave: (String) -> Unit) {
    var text by remember(initialText) { mutableStateOf(initialText) }
    Column(Modifier.padding(16.dp).fillMaxSize()) {
        Text("Conclusión del caso", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth().weight(1f, fill = false).height(160.dp),
            placeholder = { Text("Escribe las conclusiones de la investigación...") }
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = { onSave(text) }) {
            Text("Guardar conclusión")
        }
    }
}

@Composable
private fun EvidenciasTab(
    evidences: List<EvidenceEntity>,
    readOnly: Boolean,
    onAdd: () -> Unit,
    onEdit: (EvidenceEntity) -> Unit,
    onDelete: (EvidenceEntity) -> Unit
) {
    Column(Modifier.padding(16.dp).fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text("Evidencias (${evidences.size})", fontWeight = FontWeight.Bold)
            // Si el caso esta cerrado no se permite agregar nuevas evidencias.
            if (!readOnly) {
                TextButton(onClick = onAdd) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(Modifier.width(4.dp))
                    Text("Agregar")
                }
            }
        }
        if (readOnly) {
            Spacer(Modifier.height(4.dp))
            Text(
                "Este caso está cerrado: las evidencias no se pueden modificar.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(Modifier.height(8.dp))
        if (evidences.isEmpty()) {
            Text("Aún no hay evidencias registradas para este caso.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(evidences, key = { it.id }) { evidence ->
                    Card(shape = RoundedCornerShape(10.dp)) {
                        Row(Modifier.padding(12.dp), verticalAlignment = androidx.compose.ui.Alignment.Top) {
                            Column(Modifier.weight(1f)) {
                                Text(evidence.name, fontWeight = FontWeight.Bold)
                                Text(evidence.description, style = MaterialTheme.typography.bodyMedium)
                            }
                            // Los botones de editar/eliminar solo aparecen si el caso no esta cerrado.
                            if (!readOnly) {
                                IconButton(onClick = { onEdit(evidence) }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                                }
                                IconButton(onClick = { onDelete(evidence) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddInterviewDialog(
    onDismiss: () -> Unit,
    onSave: (name: String, date: String, findings: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())) }
    var findings by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva entrevista") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre del entrevistado") }, singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Fecha") }, singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = findings, onValueChange = { findings = it }, label = { Text("Hallazgos principales") })
            }
        },
        confirmButton = {
            TextButton(
                onClick = { if (name.isNotBlank()) onSave(name, date, findings) },
                enabled = name.isNotBlank()
            ) { Text("Guardar") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

// Nuevo: dialogo de edicion de entrevista.
// Recibe la entrevista original para precargar los campos, y devuelve
// (via onSave) una copia actualizada con los nuevos valores.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditInterviewDialog(
    interview: InterviewEntity,
    onDismiss: () -> Unit,
    onSave: (InterviewEntity) -> Unit
) {
    // remember(interview.id) asegura que si se abre el dialogo para otra entrevista
    // distinta, los campos se reinicien con los valores de la nueva entrevista.
    var name by remember(interview.id) { mutableStateOf(interview.personName) }
    var date by remember(interview.id) { mutableStateOf(interview.date) }
    var findings by remember(interview.id) { mutableStateOf(interview.findings) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar entrevista") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre del entrevistado") }, singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Fecha") }, singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = findings, onValueChange = { findings = it }, label = { Text("Hallazgos principales") })
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (name.isNotBlank()) {
                        // .copy() crea una nueva instancia de la data class InterviewEntity,
                        // conservando id y caseId, pero con los campos editados.
                        onSave(interview.copy(personName = name, date = date, findings = findings))
                    }
                },
                enabled = name.isNotBlank()
            ) { Text("Guardar") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

@Composable
private fun AddEvidenceDialog(
    onDismiss: () -> Unit,
    onSave: (name: String, description: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva evidencia") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre / referencia") }, singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
            }
        },
        confirmButton = {
            TextButton(
                onClick = { if (name.isNotBlank()) onSave(name, description) },
                enabled = name.isNotBlank()
            ) { Text("Guardar") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

// Nuevo: dialogo de edicion de evidencia. Mismo patron que EditInterviewDialog:
// precarga los campos con remember(evidence.id) y usa .copy() para conservar
// id y caseId al guardar los cambios.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditEvidenceDialog(
    evidence: EvidenceEntity,
    onDismiss: () -> Unit,
    onSave: (EvidenceEntity) -> Unit
) {
    var name by remember(evidence.id) { mutableStateOf(evidence.name) }
    var description by remember(evidence.id) { mutableStateOf(evidence.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar evidencia") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre / referencia") }, singleLine = true)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (name.isNotBlank()) {
                        onSave(evidence.copy(name = name, description = description))
                    }
                },
                enabled = name.isNotBlank()
            ) { Text("Guardar") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}