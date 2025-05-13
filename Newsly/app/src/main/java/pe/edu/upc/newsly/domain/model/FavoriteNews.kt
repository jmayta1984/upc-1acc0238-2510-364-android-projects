package pe.edu.upc.newsly.domain.model

data class FavoriteNews (
    val author: String,
    val title: String,
    val urlToImage: String,
    val description: String
)