package eu.yeger.r6_stats.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.yeger.r6_stats.domain.PlayerResponse
import eu.yeger.r6_stats.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatsRepository {

    private val _player = MutableLiveData<PlayerResponse>()
    val player: LiveData<PlayerResponse> = _player

    suspend fun fetchStats(playerId: String) {
        val playerResponse = withContext(Dispatchers.IO) {
            NetworkService.siegeApi.player(id = playerId)
        }
        _player.postValue(playerResponse)
    }
}