package eu.yeger.r6_stats.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.yeger.r6_stats.domain.SearchResult
import eu.yeger.r6_stats.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class SearchRepository {

    private val _searchResults = MutableLiveData<List<SearchResult>>()
    val searchResults: LiveData<List<SearchResult>> = _searchResults

    suspend fun search(platform: String, name: String) {
        try {
            val searchResponse = withContext(Dispatchers.IO) {
                NetworkService.siegeApi.search(platform = platform, name = name)
            }
            _searchResults.value = searchResponse.results
        } catch (exception: Exception) {
            Timber.e(exception)
        }
    }
}
