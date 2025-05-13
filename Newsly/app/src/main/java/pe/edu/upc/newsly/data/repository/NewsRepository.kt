package pe.edu.upc.newsly.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import pe.edu.upc.newsly.data.local.NewsDao
import pe.edu.upc.newsly.data.model.NewsEntity
import pe.edu.upc.newsly.data.model.NewsMapper
import pe.edu.upc.newsly.data.remote.NewsService
import pe.edu.upc.newsly.domain.model.FavoriteNews
import pe.edu.upc.newsly.domain.model.News

class NewsRepository(val newsService: NewsService, val newsDao: NewsDao) {

    suspend fun findNews(description: String): List<News> = withContext(Dispatchers.IO) {
        val response = newsService.findNews(description)

        if (response.isSuccessful) {
            Log.d("NewsRepository", "Response successful")
            response.body()?.let { it ->
                return@withContext it.map { newsResponse ->
                    val news = NewsMapper.toNews(newsResponse)
                    news.copy(
                        isFavorite = newsDao.fetchNewsByTitle(news.title).isNotEmpty()
                    )
                }
            }
            Log.d("NewsRepository", response.message())

        }

        return@withContext emptyList()
    }

    suspend fun insertNews(news: News) = withContext(Dispatchers.IO) {
        newsDao.insertNews(
            NewsEntity(
                title = news.title,
                author = news.author,
                description = news.description,
                poster = news.urlToImage
            )
        )
    }

    suspend fun deleteNews(news: News) = withContext(Dispatchers.IO) {
        newsDao.deleteNews(
            NewsEntity(
                title = news.title,
                author = news.author,
                description = news.description,
                poster = news.urlToImage
            )
        )
    }

}