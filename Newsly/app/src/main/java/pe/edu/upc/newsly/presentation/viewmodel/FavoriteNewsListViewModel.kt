package pe.edu.upc.newsly.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.newsly.data.repository.FavoriteNewsRepository
import pe.edu.upc.newsly.domain.model.FavoriteNews

class FavoriteNewsListViewModel(val favoriteNewsRepository: FavoriteNewsRepository) : ViewModel() {
    private val _favorites = MutableStateFlow<List<FavoriteNews>>(emptyList())
    val favorites: StateFlow<List<FavoriteNews>> = _favorites

    fun fetchNews() {
        viewModelScope.launch {
            _favorites.value = favoriteNewsRepository.fetchNews()

        }
    }

    fun deleteNews(favoriteNews: FavoriteNews) {
        viewModelScope.launch {
            favoriteNewsRepository.deleteNews(favoriteNews)
            fetchNews()
        }
    }

}