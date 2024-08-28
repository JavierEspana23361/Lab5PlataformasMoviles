package com.example.lab5_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5_plataformasmoviles.ui.theme.InfoConciertoTheme

val conciertos = listOf(
    Concierto("Concierto A", "Auditorio Nacional"),
    Concierto("Concierto B", "Estadio Azteca"),
    Concierto("Concierto C", "Foro Sol"),
    Concierto("Concierto D", "Palacio de los Deportes")
)

class ConcertListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfoConciertoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConcertListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConcertListScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Conciertos",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(conciertos) { concert ->
                ConcertRow(concert)
            }
        }
    }
}

@Composable
fun ConcertRow(concert: Concierto, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = concert.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = concert.location,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}

data class Concierto(
    val title: String,
    val location: String
)

@Preview(showBackground = true)
@Composable
fun PreviewConcertListScreen() {
    InfoConciertoTheme {
        ConcertListScreen()
    }
}

