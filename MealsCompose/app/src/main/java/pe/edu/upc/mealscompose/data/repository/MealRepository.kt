package pe.edu.upc.mealscompose.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.mealscompose.data.model.MealResponse
import pe.edu.upc.mealscompose.data.remote.MealService

class MealRepository(val mealService: MealService) {

    suspend fun getMealsByCategory(category: String): List<MealResponse> =
        withContext(Dispatchers.IO) {
            val response = mealService.getMealsByCategory(category)
            if (response.isSuccessful){
                response.body()?.let { it ->
                    return@withContext it.meals
                }
            }

            return@withContext emptyList()
        }
}