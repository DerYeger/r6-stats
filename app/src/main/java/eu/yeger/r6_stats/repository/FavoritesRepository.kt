package eu.yeger.r6_stats.repository

import android.content.Context
import eu.yeger.r6_stats.database.Favorite
import eu.yeger.r6_stats.database.getDatabase
import eu.yeger.r6_stats.domain.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesRepository(context: Context) {

    private val database = getDatabase(context)

    val favorites = database.favoriteDao.getAll()

    suspend fun addToFavorites(player: Player) {
        withContext(Dispatchers.IO) {
            database.favoriteDao.insert(Favorite(playerId = player.id))
        }
    }

    suspend fun removeFromFavorites(player: Player) {
        withContext(Dispatchers.IO) {
            database.favoriteDao.delete(Favorite(playerId = player.id))
        }
    }
}
