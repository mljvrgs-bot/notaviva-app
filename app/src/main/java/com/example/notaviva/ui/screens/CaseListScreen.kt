package com.example.notaviva.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notaviva.data.CaseEntity
import com.example.notaviva.data.CaseStatus
import com.example.notaviva.viewmodel.CaseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaseListScreen(
    viewModel: CaseViewModel,
    onBack: () -> Unit,
    onNewCase: () -> Unit,
    onOpenCase: (Long) -> Unit
) {
    val cases by viewModel.filteredCases.collectAsState()
    val query by viewModel.searchQuery.collectAsState()
    var caseToDelete by remember { mutableStateOf<CaseEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis casos") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNewCase) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo caso")
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = query,
                onValueChange = viewModel::onSearchChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar por título o tema") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            if (cases.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text("No hay casos registrados todavía.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(cases, key = { it.id }) { case ->
                        CaseRow(
                            case = case,
                            onClick = { onOpenCase(case.id) },
                            onDelete = { caseToDelete = case }
                        )
                    }
                }
            }
        }
    }

    caseToDelete?.let { case ->
        ConfirmDialog(
            title = "Eliminar caso",
            message = "¿Seguro que deseas eliminar \"${case.title}\"? Esta acción no se puede deshacer.",
            onConfirm = {
                viewModel.deleteCase(case)
                caseToDelete = null
            },
            onDismiss = { caseToDelete = null }
        )
    }
}

@Composable
private fun CaseRow(case: CaseEntity, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(shape = RoundedCornerShape(12.dp), onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(
            Modifier.padding(14.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(case.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(4.dp))
                Text(case.date, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(6.dp))
                StatusChip(CaseStatus.fromName(case.status))
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}
