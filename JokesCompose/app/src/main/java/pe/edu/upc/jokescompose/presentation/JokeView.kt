package pe.edu.upc.jokescompose.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.upc.jokescompose.data.remote.ApiClient
import pe.edu.upc.jokescompose.data.repository.JokeRepository

@Preview
@Composable
fun JokeView() {

    val content = remember {
        mutableStateOf("")
    }

    val service = ApiClient.getJokeService()
    val repository = JokeRepository(service)
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(content.value)
            OutlinedButton(
                onClick = {
                    repository.getRandomJoke { it ->
                        content.value = it.content
                    }
                }
            ) {
                Text("Random")
            }
        }

    }
}