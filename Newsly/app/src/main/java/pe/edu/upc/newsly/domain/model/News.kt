package pe.edu.upc.newsly.domain.model

import pe.edu.upc.newsly.data.model.SourceResponse

data class News(
    val author: String,
    val title: String,
    val content: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val source: String,
    var isFavorite: Boolean = false,
    val description: String
)
