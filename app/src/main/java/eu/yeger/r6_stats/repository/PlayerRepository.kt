package eu.yeger.r6_stats.repository

import android.content.Context
import androidx.lifecycle.LiveData
import eu.yeger.r6_stats.database.getDatabase
import eu.yeger.r6_stats.domain.Player
import eu.yeger.r6_stats.network.NetworkService
import eu.yeger.r6_stats.network.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PlayerRepository(context: Context, private val playerId: String?) {

    private val database = getDatabase(context)

    val currentPlayer: LiveData<Player> = database.playerDao.get(playerId ?: "")

    suspend fun fetchPlayer() {
        try {
            if (playerId == null) return

            val playerResponse = withContext(Dispatchers.IO) {
                NetworkService.siegeApi.player(id = playerId)
            }

            val player = playerResponse.toDomainModel()

            withContext(Dispatchers.IO) {
                if (currentPlayer.value === null) {
                    database.playerDao.insert(player)
                } else {
                    database.playerDao.update(player)
                }
            }
        } catch (exception: Exception) {
            Timber.e(exception)
        }
    }
}