package eu.yeger.r6_stats.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.yeger.r6_stats.ratio

@Entity
data class Player(
    @PrimaryKey
    val id: String,
    val userId: String,
    val name: String,
    val level: Int,
    val platform: String,
    @Embedded(prefix = "stats_")
    val generalStats: GeneralStats,
    @Embedded(prefix = "ranked_")
    val ranked: GameMode,
    @Embedded(prefix = "casual_")
    val casual: GameMode,
    @Embedded(prefix = "total_")
    val total: GameMode,
    @Embedded(prefix = "bomb_")
    val bomb: ObjectiveType,
    @Embedded(prefix = "secure_area_")
    val secureArea: ObjectiveType,
    @Embedded(prefix = "hostage_")
    val hostage: ObjectiveType
) {
    data class GeneralStats(
        val totalBullets: Int,
        val headshots: Int,
        val melees: Int,
        val revives: Int,
        val suicides: Int
    )
}

data class GameMode(
    val playtime: Double,
    val wins: Int,
    val losses: Int,
    val winrate: Double = ratio(wins, losses),
    val kills: Int,
    val deaths: Int,
    val kd: Double = ratio(kills, deaths)
)

data class ObjectiveType(
    val wins: Int,
    val losses: Int,
    val winrate: Double = ratio(wins, losses)
)
