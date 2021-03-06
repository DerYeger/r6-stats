package eu.yeger.r6_stats.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import eu.yeger.r6_stats.domain.GameMode
import eu.yeger.r6_stats.domain.ObjectiveType
import eu.yeger.r6_stats.domain.Player

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
)

fun PlayerResponse.toDomainModel(): Player = Player(
    name = name,
    level = level,
    platform = networkPlatformToDomainPlatform(platform),
    id = id,
    userId = user,
    generalStats = Player.GeneralStats(
        totalBullets = stats[16],
        headshots = stats[17],
        melees = stats[18],
        revives = stats[19],
        suicides = stats[20]
    ),
    ranked = GameMode(
        playtime = stats[0].toDouble() / 3600,
        kills = stats[1],
        deaths = stats[2],
        wins = stats[3],
        losses = stats[4]
    ),
    casual = GameMode(
        playtime = stats[5].toDouble() / 3600,
        kills = stats[6],
        deaths = stats[7],
        wins = stats[8],
        losses = stats[9]
    ),
    total = GameMode(
        playtime = (stats[0] + stats[5]).toDouble() / 3600,
        kills = stats[1] + stats[6],
        deaths = stats[2] + stats[7],
        wins = stats[3] + stats[8],
        losses = stats[4] + stats[9]
    ),
    bomb = ObjectiveType(
        wins = stats[10],
        losses = stats[11]
    ),
    secureArea = ObjectiveType(
        wins = stats[12],
        losses = stats[13]
    ),
    hostage = ObjectiveType(
        wins = stats[14],
        losses = stats[15]
    )
)

fun networkPlatformToDomainPlatform(platform: String) = when (platform) {
    "uplay" -> "PC"
    "psn" -> "PlayStation"
    "xbl" -> "Xbox"
    else -> platform
}
