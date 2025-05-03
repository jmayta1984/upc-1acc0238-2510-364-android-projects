package pe.edu.upc.mealscompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.mealscompose.data.remote.ApiClient
import pe.edu.upc.mealscompose.data.repository.CategoryRepository

@Preview
@Composable
fun CategoryListView(
    onTap: (String?) -> Unit = {}
) {
    val categoryService = ApiClient.getCategoryService()
    val categoryRepository = CategoryRepository(categoryService)
    val categoryListViewModel = CategoryListViewModel(categoryRepository)
    val categories = categoryListViewModel.categories.collectAsState()

    categoryListViewModel.getCategories()


    Scaffold { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(categories.value) { category ->
                Card(modifier = Modifier.padding(8.dp), onClick = { onTap(category.name) }) {
                    Row {
                        AsyncImage(
                            model = category.poster,
                            contentDescription = null,
                            modifier = Modifier.size(96.dp)
                        )
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(category.name ?: "", fontWeight = FontWeight.Bold)
                            Text(category.description ?: "", maxLines = 2)

                        }
                    }
                }

            }
        }
    }
}
