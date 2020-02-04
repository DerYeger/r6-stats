package eu.yeger.r6_stats.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.yeger.r6_stats.repository.StatsRepository
import kotlinx.coroutines.launch

class StatsViewModel(playerId: String?) : ViewModel() {

    private val statsRepository = StatsRepository()

    val player = statsRepository.player

    init {
        playerId?.let {
            viewModelScope.launch {
                statsRepository.fetchStats(playerId)
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