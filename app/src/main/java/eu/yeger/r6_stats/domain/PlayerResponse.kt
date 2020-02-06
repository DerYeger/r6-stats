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
    val rankedPlaytime = stats[0]
    val rankedKills = stats[1]
    val rankedDeaths = stats[2]
    val rankedWins = stats[3]
    val rankedLosses = stats[4]

    val casualPlaytime = stats[5]
    val casualKills = stats[6]
    val casualDeaths = stats[7]
    val casualWins = stats[8]
    val casualLosses = stats[9]

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
