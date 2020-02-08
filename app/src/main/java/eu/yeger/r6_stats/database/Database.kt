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

@Database(
    entities = [Player::class],
    version = 1,
    exportSchema = false
)
abstract class PlayerDatabase : RoomDatabase() {
    abstract val playerDao: PlayerDao
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
                .build()
        }
        return INSTANCE
    }
}
