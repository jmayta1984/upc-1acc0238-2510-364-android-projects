package pe.edu.upc.newsly.data.model

import pe.edu.upc.newsly.domain.model.News

object NewsMapper {

    fun toNews(newsResponse: NewsResponse): News {
        return News(
            author = newsResponse.author ?: "",
            title = newsResponse.title ?: "",
            content = newsResponse.content ?: "",
            url = newsResponse.url ?: "",
            urlToImage = newsResponse.urlToImage ?: "",
            publishedAt = newsResponse.publishedAt ?: "",
            source = newsResponse.source?.name ?: "",
            description = newsResponse.description ?: ""


        )
    }
}