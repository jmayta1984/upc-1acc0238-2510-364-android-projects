package pe.edu.upc.newsly.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.newsly.domain.model.News
import pe.edu.upc.newsly.presentation.di.PresentationModule
import pe.edu.upc.newsly.presentation.viewmodel.NewsSearchViewModel

@Composable
fun NewsSearchView(
    viewModel: NewsSearchViewModel = PresentationModule.getNewsSearchViewModel(),
    onTap: (News) -> Unit
) {

    val description = remember {
        mutableStateOf("")
    }

    val news = viewModel.news.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            description.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onValueChange = { it -> description.value = it })
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                viewModel.findNews(description.value)
            }
        ) {
            Text("Find news")
        }
        LazyColumn {
            items(news.value) { news ->
                NewsListItemView(news, onTap = onTap) { isFavorite ->
                    if (isFavorite) {
                        viewModel.insertNews(news)
                    } else {
                        viewModel.deleteNews(news)
                    }
                }
            }
        }

    }
}

@Composable
fun NewsListItemView(
    news: News,
    onTap: (News) -> Unit,
    toggleFavorite: (Boolean) -> Unit
) {
    val isFavorite = remember {
        mutableStateOf(news.isFavorite)
    }
    Card(modifier = Modifier.padding(8.dp), onClick = { onTap(news) }) {

        Column {
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
                        toggleFavorite(isFavorite.value)
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
            Column(modifier = Modifier.padding(8.dp)) {
                Text(news.title, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(news.publishedAt)

            }
        }
    }
}