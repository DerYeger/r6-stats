package eu.yeger.r6_stats.ui.stats

import android.app.Application
import androidx.lifecycle.*
import eu.yeger.r6_stats.repository.FavoritesRepository
import eu.yeger.r6_stats.repository.StatsRepository
import kotlinx.coroutines.launch

class StatsViewModel(application: Application, val playerId: String?) : ViewModel() {

    private val statsRepository = StatsRepository(application, playerId)
    private val favoritesRepository = FavoritesRepository(application)

    val player = statsRepository.currentPlayer
    val hasPlayer = Transformations.map(player) { it !== null }

    val isFavorite = favoritesRepository.isFavorite(playerId)

    private val _refreshing = MutableLiveData<Boolean>()
    val refreshing: LiveData<Boolean> = Transformations.map(_refreshing) {
        it && playerId !== null
    }

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _refreshing.value = true
            statsRepository.fetchPlayer()
            _refreshing.value = false
        }
    }

    fun onFavoriteToggled() {
        playerId?.let {
            viewModelScope.launch {
                if (isFavorite.value != null) {
                    favoritesRepository.removeFromFavorites(playerId)
                } else {
                    favoritesRepository.addToFavorites(playerId)
                }
            }
        }
    }

    class Factory(
        private val application: Application,
        private val playerId: String?
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StatsViewModel(application, playerId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}