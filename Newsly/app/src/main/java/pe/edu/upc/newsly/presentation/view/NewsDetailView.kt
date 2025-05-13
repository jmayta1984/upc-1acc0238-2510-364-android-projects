package pe.edu.upc.newsly.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.newsly.domain.model.News

@Composable
fun NewsDetailView(news: News) {
    val isFavorite = remember {
        mutableStateOf(news.isFavorite)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            AsyncImage(
                model = news.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp),
                contentScale = ContentScale.Crop
            )
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.TopEnd)
                    .background(Color.White)
                    .padding(2.dp),
                onClick = {
                    isFavorite.value = !isFavorite.value
                    news.isFavorite = isFavorite.value
                }) {
                Icon(
                    if (isFavorite.value) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder

                    }, contentDescription = null
                )
            }

        }

        OutlinedButton(
            onClick = {

            }
        ) {
            Text("URL")
        }


    }
}