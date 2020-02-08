package eu.yeger.r6_stats.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.yeger.r6_stats.domain.Player
import eu.yeger.r6_stats.network.NetworkService
import eu.yeger.r6_stats.network.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatsRepository {

    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player> = _player

    suspend fun fetchStats(playerId: String) {
        val playerResponse = withContext(Dispatchers.IO) {
            NetworkService.siegeApi.player(id = playerId)
        }
        val player = playerResponse.toDomainModel()
        _player.postValue(player)
    }
}