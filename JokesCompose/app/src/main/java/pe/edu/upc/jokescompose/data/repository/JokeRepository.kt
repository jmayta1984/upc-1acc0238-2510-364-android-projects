package pe.edu.upc.jokescompose.data.repository

import pe.edu.upc.jokescompose.data.model.Joke
import pe.edu.upc.jokescompose.data.remote.JokeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeRepository(val jokeService: JokeService) {

    fun getRandomJoke(callback: (Joke) -> Unit){
        jokeService.getRandomJoke().enqueue(object: Callback<Joke> {
            override fun onResponse(
                call: Call<Joke?>,
                response: Response<Joke?>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { it ->
                        callback(it)
                    }
                }
            }

            override fun onFailure(
                call: Call<Joke?>,
                t: Throwable
            ) {
            }
        })
    }
}