package pe.edu.upc.newsly.data.model

data class NewsResponse(
    val author: String?,
    val title: String?,
    val content: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val source: SourceResponse?,
    val description: String?


)

data class SourceResponse (
    val name: String?
)