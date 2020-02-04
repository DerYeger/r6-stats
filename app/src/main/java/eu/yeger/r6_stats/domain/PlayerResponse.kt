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
)