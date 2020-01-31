package eu.yeger.r6_stats.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val results: List<SearchResult>?,
    @Json(name = "totalresults")
    val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class SearchResult(
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
    @Json(name = "p_currentmmr")
    val currentMMR: Int,
    @Json(name = "p_currentrank")
    val currentRank: Int,
    @Json(name = "verified")
    val verified: Int,
    @Json(name = "kd")
    val kd: Int
)
