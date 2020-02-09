package eu.yeger.r6_stats.ui.favorites

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.yeger.r6_stats.repository.FavoritesRepository

class FavoritesViewModel(application: Application) : ViewModel() {

    private val favoritesRepository = FavoritesRepository(application)

    val favorites = favoritesRepository.favorites

    class Factory(
        private val application: Application
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoritesViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
