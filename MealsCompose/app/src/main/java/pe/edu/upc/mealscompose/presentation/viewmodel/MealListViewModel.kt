package pe.edu.upc.mealscompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.mealscompose.data.model.MealResponse
import pe.edu.upc.mealscompose.data.repository.MealRepository

class MealListViewModel(val mealRepository: MealRepository): ViewModel() {

    private val _meals = MutableStateFlow<List<MealResponse>>(emptyList())
    val meals: StateFlow<List<MealResponse>> = _meals

    fun getMealsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _meals.value = mealRepository.getMealsByCategory(category)
        }

    }

    fun insertMeal(id: String, name: String){
        viewModelScope.launch {
            mealRepository.insertMeal(id, name)
        }
    }
}