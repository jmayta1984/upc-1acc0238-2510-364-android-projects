package pe.edu.upc.newsly.data.remote

import pe.edu.upc.newsly.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("articles.php")
    suspend fun findNews(@Query("description") description: String): Response<List<NewsResponse>>
}