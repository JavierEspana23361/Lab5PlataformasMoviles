package com.example.lab5_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5_plataformasmoviles.ui.theme.InfoConciertoTheme


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
    val imageResId = when (concert.imageTitle) {
        "concert_a" -> R.drawable.concert_image_1
        "concert_b" -> R.drawable.concert_image_1
        "concert_c" -> R.drawable.concert_image_1
        "concert_d" -> R.drawable.concert_image_1
        "concert_e" -> R.drawable.concert_image_1
        else -> R.drawable.concert_image_1
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row {
            Box(modifier = modifier
                    .padding(horizontal = 4.dp)

            ){
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentScale = ContentScale.Crop
                )
            }
            Box(modifier = Modifier
                    .padding(vertical = 4.dp)
            ){
                Text(
                    text = concert.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }



        }
        Row (modifier = Modifier
            .padding(horizontal = 34.dp)
            .padding(vertical = 4.dp)

            ){
            Box(modifier = Modifier
                    .padding(horizontal = 8.dp)
            ){
                Text(
                    text = concert.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Box(modifier = Modifier
                .padding(horizontal = 16.dp)
            ){
                Text(
                    text = concert.location,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

        }


    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}



@Preview(showBackground = true)
@Composable
fun PreviewConcertListScreen() {
    InfoConciertoTheme {
        ConcertListScreen()
    }
}
