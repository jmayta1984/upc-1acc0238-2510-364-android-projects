package pe.edu.upc.mealscompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.upc.mealscompose.data.remote.ApiClient
import pe.edu.upc.mealscompose.data.repository.CategoryRepository
import pe.edu.upc.mealscompose.presentation.CategoryListViewModel

@Preview
@Composable
fun CategoryListView() {
    val categoryService = ApiClient.getCategoryService()
    val categoryRepository = CategoryRepository(categoryService)
    val categoryListViewModel = CategoryListViewModel(categoryRepository)
    val categories = categoryListViewModel.categories.collectAsState()

    categoryListViewModel.getCategories()

    Scaffold { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(categories.value) { category ->
                Text(category.name ?: "")

            }
        }
    }
}
