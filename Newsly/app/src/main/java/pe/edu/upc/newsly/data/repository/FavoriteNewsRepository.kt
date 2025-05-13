package pe.edu.upc.newsly.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.newsly.data.local.NewsDao
import pe.edu.upc.newsly.data.model.NewsEntity
import pe.edu.upc.newsly.domain.model.FavoriteNews

class FavoriteNewsRepository(val newsDao: NewsDao) {

    suspend fun fetchNews(): List<FavoriteNews> = withContext(Dispatchers.IO) {
        return@withContext newsDao.fetchNews().map { newsEntity ->
            FavoriteNews(
                title = newsEntity.title,
                author = newsEntity.author,
                description = newsEntity.description,
                urlToImage = newsEntity.poster
            )
        }

    }

    suspend fun deleteNews(favoriteNews: FavoriteNews) = withContext(Dispatchers.IO) {
        newsDao.deleteNews(
            NewsEntity(
                title = favoriteNews.title,
                author = favoriteNews.author,
                description = favoriteNews.description,
                poster = favoriteNews.urlToImage
            )
        )
    }
}