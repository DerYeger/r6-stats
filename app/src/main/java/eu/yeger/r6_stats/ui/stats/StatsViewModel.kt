package eu.yeger.r6_stats.ui.stats

import androidx.lifecycle.*
import eu.yeger.r6_stats.repository.StatsRepository
import kotlinx.coroutines.launch

class StatsViewModel(val playerId: String?) : ViewModel() {

    private val statsRepository = StatsRepository()

    val player = statsRepository.player
    val hasPlayer = Transformations.map(player) { it !== null }

    private val _searchInProgress = MutableLiveData<Boolean>()
    val progressBarVisible: LiveData<Boolean> = Transformations.map(_searchInProgress) {
        it && playerId !== null
    }

    init {
        playerId?.let {
            viewModelScope.launch {
                _searchInProgress.value = true
                statsRepository.fetchStats(playerId)
                _searchInProgress.value = false
            }
        }
    }

    class Factory(
        private val playerId: String?
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StatsViewModel(playerId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}