package com.example.lab5_plataformasmoviles
//
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5_plataformasmoviles.ui.theme.InfoConciertoTheme

class ConcertDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfoConciertoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConcertDetailScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConcertDetailScreen(modifier: Modifier = Modifier) {
    val concert = ConcertDetail(
        title = "Concierto A",
        location = "Auditorio",
        date = "12 de Octubre, 2024",
        description = "Un concierto.",
        imageResId = R.drawable.concert_image_1
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = concert.imageResId),
            contentDescription = "Imagen del Concierto",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = concert.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = concert.location,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = concert.date,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = concert.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

data class ConcertDetail(
    val title: String,
    val location: String,
    val date: String,
    val description: String,
    val imageResId: Int
)

@Preview(showBackground = true)
@Composable
fun PreviewConcertDetailScreen() {
    InfoConciertoTheme {
        ConcertDetailScreen()
    }
}
