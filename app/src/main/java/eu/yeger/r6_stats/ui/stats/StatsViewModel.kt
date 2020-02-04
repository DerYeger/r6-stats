package eu.yeger.r6_stats.ui.stats

import android.view.View
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.yeger.r6_stats.repository.StatsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class StatsViewModel(playerId: String?) : ViewModel() {

    private val statsRepository = StatsRepository()

    val player = statsRepository.player

    val errorVisibility = Transformations.map(player) {
        if (it == null) View.VISIBLE else View.GONE
    }

    val statsVisibility = Transformations.map(player) {
        if (it == null) View.GONE else View.VISIBLE
    }

    init {
        playerId?.let {
            viewModelScope.launch {
                statsRepository.fetchStats(playerId)
                Timber.i(player.value?.name)
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