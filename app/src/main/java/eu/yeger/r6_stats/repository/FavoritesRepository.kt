package eu.yeger.r6_stats.repository

import android.content.Context
import eu.yeger.r6_stats.database.Favorite
import eu.yeger.r6_stats.database.getDatabase
import eu.yeger.r6_stats.domain.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesRepository(context: Context) {

    private val database = getDatabase(context)

    val favorites by lazy {
        database.favoriteDao.getAll()
    }

    fun isFavorite(playerId: String?) = database.favoriteDao.get(playerId ?: "")

    suspend fun addToFavorites(playerId: String) {
        withContext(Dispatchers.IO) {
            database.favoriteDao.insert(Favorite(playerId))
        }
    }

    suspend fun removeFromFavorites(playerId: String) {
        withContext(Dispatchers.IO) {
            database.favoriteDao.delete(Favorite(playerId))
        }
    }
}
