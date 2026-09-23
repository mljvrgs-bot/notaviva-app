package com.example.notaviva.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notaviva.data.CaseStatus
import com.example.notaviva.util.CaseUtils
import com.example.notaviva.viewmodel.CaseViewModel

@Composable
fun HomeScreen(
    viewModel: CaseViewModel,
    onNewCase: () -> Unit,
    onMyCases: () -> Unit
) {
    val cases by viewModel.allCases.collectAsState()

    val totalCases = cases.size

    val casesInResearch =
        CaseUtils.countByStatus(
            cases,
            CaseStatus.EN_INVESTIGACION
        )

    val publishedCases =
        CaseUtils.countByStatus(
            cases,
            CaseStatus.PUBLICADO
        )

    val closedCases =
        CaseUtils.countByStatus(
            cases,
            CaseStatus.CERRADO
        )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Column {

                Text(
                    text = "NotaViva",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Historias que importan",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.primary
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Bienvenida a NotaViva",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Organiza tus investigaciones, casos y entrevistas desde un solo lugar.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
        }

        item {
            Text(
                text = "Resumen",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                SummaryCard(
                    title = "Casos",
                    value = totalCases.toString(),
                    modifier = Modifier.weight(1f)
                )

                SummaryCard(
                    title = "En investigación",
                    value = casesInResearch.toString(),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                SummaryCard(
                    title = "Publicados",
                    value = publishedCases.toString(),
                    modifier = Modifier.weight(1f)
                )

                SummaryCard(
                    title = "Cerrados",
                    value = closedCases.toString(),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Text(
                text = "Acciones rápidas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Nueva entrevista",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "Las entrevistas se registran dentro de cada caso.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    OutlinedButton(
                        onClick = onMyCases,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ir a mis casos")
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Button(
                    onClick = onNewCase,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Nuevo caso")
                }

                OutlinedButton(
                    onClick = onMyCases,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Mis casos")
                }
            }
        }

        item {
            Text(
                text = "Casos recientes",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        if (cases.isEmpty()) {

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "No hay casos registrados",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "Crea tu primer caso para comenzar a organizar tu investigación.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Button(
                            onClick = onNewCase
                        ) {
                            Text("Crear caso")
                        }
                    }
                }
            }

        } else {

            items(
                items = cases.take(5),
                key = { case -> case.id }
            ) { case ->

                CaseCard(
                    title = case.title,
                    description = case.description,
                    date = case.date,
                    status = case.status
                )
            }
        }
    }
}

@Composable
private fun SummaryCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CaseCard(
    title: String,
    description: String,
    date: String,
    status: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Fecha: $date",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Estado: ${getStatusLabel(status)}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

private fun getStatusLabel(status: String): String {
    return when (status) {
        CaseStatus.EN_INVESTIGACION.name ->
            CaseStatus.EN_INVESTIGACION.label

        CaseStatus.PUBLICADO.name ->
            CaseStatus.PUBLICADO.label

        CaseStatus.CERRADO.name ->
            CaseStatus.CERRADO.label

        else ->
            status
    }
}