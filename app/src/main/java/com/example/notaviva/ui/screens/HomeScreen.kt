package com.example.notaviva.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("NotaViva", style = MaterialTheme.typography.titleLarge)
        Text(
            "Historias que importan",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.height(20.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text(
                    "Hola, periodista",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Organiza tus entrevistas y construye tus historias.",
                    color = Color.White.copy(alpha = 0.85f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Resumen general", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SummaryCard("Total", cases.size.toString(), Modifier.weight(1f))
            SummaryCard(
                "Investigación",
                CaseUtils.countByStatus(cases, CaseStatus.EN_INVESTIGACION).toString(),
                Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SummaryCard(
                "Publicados",
                CaseUtils.countByStatus(cases, CaseStatus.PUBLICADO).toString(),
                Modifier.weight(1f)
            )
            SummaryCard(
                "Cerrados",
                CaseUtils.countByStatus(cases, CaseStatus.CERRADO).toString(),
                Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(24.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ActionCard("Nuevo caso", "Crea un nuevo caso", Modifier.weight(1f), onNewCase)
            ActionCard("Mis casos", "Revisa y gestiona tus investigaciones", Modifier.weight(1f), onMyCases)
        }
    }
}

@Composable
private fun SummaryCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(12.dp)) {
        Column(
            Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun ActionCard(title: String, subtitle: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        onClick = onClick
    ) {
        Column(Modifier.padding(16.dp).height(90.dp), verticalArrangement = Arrangement.Center) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
}
