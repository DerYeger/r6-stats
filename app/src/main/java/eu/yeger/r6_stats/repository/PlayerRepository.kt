package eu.yeger.r6_stats.repository

import android.content.Context
import androidx.lifecycle.LiveData
import eu.yeger.r6_stats.database.getDatabase
import eu.yeger.r6_stats.domain.Player
import eu.yeger.r6_stats.network.NetworkService
import eu.yeger.r6_stats.network.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerRepository(context: Context, private val playerId: String?) {

    private val database = getDatabase(context)

    val currentPlayer: LiveData<Player> = database.playerDao.get(playerId ?: "")

    suspend fun fetchPlayer() {
        if (playerId == null) return
        withContext(Dispatchers.IO) {
            val playerResponse = NetworkService.siegeApi.player(id = playerId)
            val player = playerResponse.toDomainModel()
            if (currentPlayer.value === null) {
                database.playerDao.insert(player)
            } else {
                database.playerDao.update(player)
            }
        }
    }
}
