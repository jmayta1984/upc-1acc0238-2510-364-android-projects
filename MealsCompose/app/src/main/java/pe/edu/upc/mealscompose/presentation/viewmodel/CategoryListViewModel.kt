package pe.edu.upc.mealscompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.mealscompose.data.model.CategoryResponse
import pe.edu.upc.mealscompose.data.repository.CategoryRepository

class CategoryListViewModel(val categoryRepository: CategoryRepository) : ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryResponse>>(emptyList())
    val categories: StateFlow<List<CategoryResponse>> = _categories

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = categoryRepository.getCategories()

        }
    }
}