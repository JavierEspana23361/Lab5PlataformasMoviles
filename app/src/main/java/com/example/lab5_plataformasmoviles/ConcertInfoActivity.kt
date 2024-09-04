package com.example.lab5_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5_plataformasmoviles.ui.theme.InfoConciertoTheme

data class Concierto(
    val title: String,
    val location: String,
    val date: String,
    val description: String,
    val imageTitle: String,
    var isFavorite: Boolean = false
)

val conciertos = listOf(
    Concierto("Concierto A", "Auditorio Nacional", "12 de Octubre", "Un concierto", "concert_a"),
    Concierto("Concierto B", "Estadio Azteca", "12 de Octubre", "Un concierto", "concert_b"),
    Concierto("Concierto C", "Foro Sol", "12 de Octubre", "Un concierto", "concert_c"),
    Concierto("Concierto D", "Palacio de los Deportes", "12 de Octubre", "Un concierto", "concert_d"),
    Concierto("Concierto E", "Estadio Maya", "12 de Octubre", "Un concierto", "concert_e")
)

class ConcertInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfoConciertoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConcertInfoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConcertInfoScreen(modifier: Modifier = Modifier) {
    val concertsList = remember { mutableStateListOf(*conciertos.toTypedArray()) }

    Row {
        Text(
            text = "TodoEventos",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,



            )
        Divider(modifier = Modifier.padding(vertical = 30.dp))


    }


    LazyColumn(modifier = Modifier.fillMaxSize()
        .padding(20.dp)
    ) {
        // Sección de Favoritos
        item {
            Text(

                text = "Favoritos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

        }
        val favoriteConcerts = concertsList.filter { it.isFavorite }
        itemsIndexed(favoriteConcerts.chunked(2)) { _, pair ->
            Row(modifier = Modifier.fillMaxWidth()) {
                for (concert in pair) {
                    ConcertCard(concert, modifier = Modifier.weight(1f), onFavoriteClick = {
                        concert.isFavorite = !concert.isFavorite
                    })
                }
            }
        }

        // Sección de Todos los Conciertos
        item {
            Text(
                text = "Todos los Conciertos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        val nonFavoriteConcerts = concertsList.filter { !it.isFavorite }
        itemsIndexed(nonFavoriteConcerts.chunked(2)) { _, pair ->
            Row(modifier = Modifier.fillMaxWidth()) {
                for (concert in pair) {
                    ConcertCard(concert, modifier = Modifier.weight(1f), onFavoriteClick = {
                        concert.isFavorite = !concert.isFavorite
                    })
                }
            }
        }
    }
}

@Composable
fun ConcertCard(concert: Concierto, modifier: Modifier = Modifier, onFavoriteClick: () -> Unit) {
    val imageResId = when (concert.imageTitle) {
        "concert_a" -> R.drawable.concert_image_1
        "concert_b" -> R.drawable.concert_image_1
        "concert_c" -> R.drawable.concert_image_1
        "concert_d" -> R.drawable.concert_image_1
        "concert_e" -> R.drawable.concert_image_1
        else -> R.drawable.concert_image_1
    }

    Card(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f), // Hace que las tarjetas sean cuadradas
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = concert.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Llena el espacio disponible para la imagen
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = concert.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = concert.date,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            IconButton(onClick = onFavoriteClick, modifier = Modifier.align(Alignment.End)) {
                Icon(
                    imageVector = if (concert.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (concert.isFavorite) "Eliminar de favoritos" else "Añadir a favoritos"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConcertInfoScreen() {
    InfoConciertoTheme {
        ConcertInfoScreen()
    }
}