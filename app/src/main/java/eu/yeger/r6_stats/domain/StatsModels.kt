package eu.yeger.r6_stats.domain

import eu.yeger.r6_stats.ratio

data class Player(
    val info: Info,
    val generalStats: GeneralStats,
    val ranked: GameMode,
    val casual: GameMode,
    val total: GameMode,
    val bomb: ObjectiveType,
    val secureArea: ObjectiveType,
    val hostage: ObjectiveType
) {
    data class Info(
        val name: String,
        val level: Int,
        val platform: String,
        val id: String,
        val userId: String
    )

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
    val kills: Int,
    val deaths: Int
) {
    val winrate = ratio(wins, losses)
    val kd = ratio(kills, deaths)
}

data class ObjectiveType(
    val wins: Int,
    val losses: Int
) {
    val winrate = ratio(wins, losses)
}
