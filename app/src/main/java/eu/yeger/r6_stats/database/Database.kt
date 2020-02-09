package eu.yeger.r6_stats.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import eu.yeger.r6_stats.domain.Player

@Dao
interface PlayerDao {

    @Insert
    fun insert(player: Player)

    @Update
    fun update(player: Player)

    @Query("SELECT * FROM player")
    fun getAll(): LiveData<List<Player>>

    @Query("SELECT * FROM player WHERE player.id == :id")
    fun get(id: String): LiveData<Player>
}

@Dao
interface FavoriteDao {
    @Query("SELECT player.* FROM favorite INNER JOIN player WHERE favorite.playerId == player.id ORDER BY player.name ASC")
    fun getAll(): LiveData<List<Player>>

    @Query("SELECT * FROM player WHERE player.id == :playerId AND player.id IN favorite")
    fun get(playerId: String): LiveData<Player>

    @Insert
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}

@Database(
    entities = [Player::class, Favorite::class],
    version = 2,
    exportSchema = false
)
abstract class PlayerDatabase : RoomDatabase() {
    abstract val playerDao: PlayerDao
    abstract val favoriteDao: FavoriteDao
}

private lateinit var INSTANCE: PlayerDatabase

fun getDatabase(context: Context): PlayerDatabase {
    synchronized(PlayerDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    PlayerDatabase::class.java,
                    "playerDatabase"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
        return INSTANCE
    }
}
