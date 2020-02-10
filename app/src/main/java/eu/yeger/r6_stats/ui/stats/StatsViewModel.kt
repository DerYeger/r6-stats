package eu.yeger.r6_stats.ui.stats

import android.app.Application
import androidx.lifecycle.*
import eu.yeger.r6_stats.repository.FavoritesRepository
import eu.yeger.r6_stats.repository.PlayerRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class StatsViewModel(application: Application, val playerId: String?) : ViewModel() {

    private val playerRepository = PlayerRepository(application, playerId)
    private val favoritesRepository = FavoritesRepository(application)

    val player = playerRepository.currentPlayer
    val hasPlayer = Transformations.map(player) { it !== null }

    val isFavorite = favoritesRepository.isFavorite(playerId)

    private val _refreshing = MutableLiveData<Boolean>()
    val refreshing: LiveData<Boolean> = Transformations.map(_refreshing) {
        it && playerId !== null
    }

    private val _networkExceptionAction = MutableLiveData<String>()
    val networkExceptionAction: LiveData<String> = _networkExceptionAction

    private val networkExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _refreshing.value = false
        _networkExceptionAction.value = exception.message ?: "Failed to fetch profile"
    }

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(networkExceptionHandler) {
            _refreshing.value = true
            playerRepository.fetchPlayer()
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

    fun onNetworkExceptionActionCompleted() {
        _networkExceptionAction.value = null
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