package pe.edu.upc.mealscompose.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.mealscompose.domain.Meal

import pe.edu.upc.mealscompose.presentation.di.PresentationModule
import pe.edu.upc.mealscompose.presentation.viewmodel.MealListViewModel

@Composable
fun MealListview(
    category: String,
    viewModel: MealListViewModel = PresentationModule.getMealListViewModel()
) {

    viewModel.getMealsByCategory(category)
    val meals = viewModel.meals.collectAsState()

    LazyColumn(modifier = Modifier.padding()) {
        items(meals.value) { meal ->
            MealListItemView(meal) { isFavorite ->
                if (isFavorite) {
                    viewModel.insertMeal(meal)
                } else {
                    viewModel.deleteMeal(meal)
                }
            }
        }
    }
}


@Composable
fun MealListItemView(meal: Meal, toggleFavorite: (Boolean) -> Unit) {

    val isFavorite = remember {
        mutableStateOf(meal.isFavorite)
    }
    Card(modifier = Modifier.padding(8.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                AsyncImage(
                    model = meal.poster,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = {
                        isFavorite.value = !isFavorite.value
                        meal.isFavorite = isFavorite.value
                        toggleFavorite(isFavorite.value)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        if (isFavorite.value) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = null
                    )
                }
            }
            Text(
                meal.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier.padding(8.dp)
            )

        }
    }

}