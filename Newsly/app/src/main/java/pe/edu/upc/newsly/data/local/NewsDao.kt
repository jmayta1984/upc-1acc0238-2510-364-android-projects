package pe.edu.upc.newsly.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.upc.newsly.data.model.NewsEntity

@Dao
interface NewsDao {

    @Query("select * from news")
    suspend fun fetchNews(): List<NewsEntity>

    @Insert
    suspend fun insertNews(newsEntity: NewsEntity)

    @Delete
    suspend fun deleteNews(newsEntity: NewsEntity)

    @Query("select * from news where title =:title")
    suspend fun fetchNewsByTitle(title: String): List<NewsEntity>


}