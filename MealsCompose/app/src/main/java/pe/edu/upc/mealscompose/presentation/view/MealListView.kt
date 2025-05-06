package pe.edu.upc.mealscompose.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.mealscompose.data.remote.ApiConstants
import pe.edu.upc.mealscompose.data.repository.MealRepository
import pe.edu.upc.mealscompose.presentation.di.PresentationModule
import pe.edu.upc.mealscompose.presentation.viewmodel.MealListViewModel

@Composable
fun MealListview(
    category: String,
    viewModel: MealListViewModel = PresentationModule.getMealListViewModel()
) {

    viewModel.getMealsByCategory(category)
    val meals = viewModel.meals.collectAsState()

    Scaffold { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(meals.value) { meal ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        AsyncImage(
                            model = meal.poster,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(128.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(meal.name ?: "", fontWeight = FontWeight.Bold, maxLines = 1)

                    }
                }

            }
        }
    }
}