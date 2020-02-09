package eu.yeger.r6_stats.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import eu.yeger.r6_stats.domain.Player

@Entity
data class Favorite(
    @ForeignKey(
        entity = Player::class,
        childColumns = ["playerId"],
        parentColumns = ["id"],
        onDelete = CASCADE
    )
    @PrimaryKey
    val playerId: String
)
