package eu.yeger.r6_stats.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val SIEGE_API_BASE_URL = "https://r6tab.com/api/"

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface SiegeApi {

    @GET("search.php?platform=uplay&search=DerYeger")
    suspend fun search(@Query("platform") platform: String = "uplay", @Query("search") name: String): SearchResponse
}

object NetworkService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(SIEGE_API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val siegeApi: SiegeApi = retrofit.create(SiegeApi::class.java)
}
