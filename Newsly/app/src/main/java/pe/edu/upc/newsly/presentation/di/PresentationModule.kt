package pe.edu.upc.newsly.presentation.di

import pe.edu.upc.newsly.data.di.DataModule
import pe.edu.upc.newsly.presentation.viewmodel.FavoriteNewsListViewModel
import pe.edu.upc.newsly.presentation.viewmodel.NewsSearchViewModel

object PresentationModule {

    fun getNewsSearchViewModel(): NewsSearchViewModel {
        return NewsSearchViewModel(DataModule.getNewsRepository())
    }

    fun getFavoritesNewsListViewModel(): FavoriteNewsListViewModel {
        return FavoriteNewsListViewModel(DataModule.getFavoriteNewsRepository())
    }
}