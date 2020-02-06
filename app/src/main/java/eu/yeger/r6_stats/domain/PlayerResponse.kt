package eu.yeger.r6_stats.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerResponse(
    @Json(name = "playerfound")
    val playerFound: Boolean,
    @Json(name = "ranked")
    val rankedStats: Map<String, Any>,
    @Json(name = "social")
    val social: Map<String, Any>,
    @Json(name = "seasonal")
    val seasonalStats: Map<String, Any>,
    @Json(name = "operators")
    val operatorStats: String,
    @Json(name = "data")
    val stats: List<Int>,
    @Json(name = "p_id")
    val id: String,
    @Json(name = "p_name")
    val name: String,
    @Json(name = "p_level")
    val level: Int,
    @Json(name = "p_platform")
    val platform: String,
    @Json(name = "p_user")
    val user: String,
    @Json(name = "verified")
    val verified: Int,
    @Json(name = "kd")
    val kd: Int
) {
    val ranked = GameMode(
        playtime = stats[0].toDouble() / 360,
        kills = stats[1],
        deaths = stats[2],
        wins = stats[3],
        losses = stats[4]
    )

    val casual = GameMode(
        playtime = stats[5].toDouble() / 360,
        kills = stats[6],
        deaths = stats[7],
        wins = stats[8],
        losses = stats[9]
    )

    val total = GameMode(
        playtime = (stats[0] + stats[5]).toDouble() / 360,
        kills = stats[1] + stats[6],
        deaths = stats[2] + stats[7],
        wins = stats[3] + stats[8],
        losses = stats[4] + stats[9]
    )

    val bombWins = stats[10]
    val bombLosses = stats[11]

    val secureWins = stats[12]
    val secureLosses = stats[13]

    val hostageWins = stats[14]
    val hostageLosses = stats[15]

    val totalBullets = stats[16]
    val headshots = stats[17]
    val melees = stats[18]
    val revives = stats[19]
    val suicides = stats[20]
}

data class GameMode(
    val playtime: Double,
    val wins: Int,
    val losses: Int,
    val kills: Int,
    val deaths: Int
)
