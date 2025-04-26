package pe.edu.upc.jokescompose.data.remote

import pe.edu.upc.jokescompose.data.remote.JokeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "https://icanhazdadjoke.com/"

    fun getJokeService(): JokeService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeService::class.java)
    }
}