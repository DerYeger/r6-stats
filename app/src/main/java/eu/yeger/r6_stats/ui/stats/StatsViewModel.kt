package eu.yeger.r6_stats.ui.stats

import android.app.Application
import androidx.lifecycle.*
import eu.yeger.r6_stats.repository.StatsRepository
import kotlinx.coroutines.launch

class StatsViewModel(application: Application, val playerId: String?) : ViewModel() {

    private val statsRepository = StatsRepository(application, playerId ?: "")

    val player = statsRepository.currentPlayer
    val hasPlayer = Transformations.map(player) { it !== null }

    private val _refreshing = MutableLiveData<Boolean>()
    val refreshing: LiveData<Boolean> = Transformations.map(_refreshing) {
        it && playerId !== null
    }

    init {
        refresh()
    }

    fun refresh() {
        playerId?.let {
            viewModelScope.launch {
                _refreshing.value = true
                statsRepository.fetchPlayer(playerId)
                _refreshing.value = false
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