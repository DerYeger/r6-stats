package eu.yeger.r6_stats.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.yeger.r6_stats.network.NetworkService
import eu.yeger.r6_stats.network.SearchResponse
import eu.yeger.r6_stats.network.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {

    val searchString = MutableLiveData<String>()

    private val _searchResults = MutableLiveData<List<SearchResult>>()
    val searchResults: LiveData<List<SearchResult>>
        get() = _searchResults

    private val _searchInProgress = MutableLiveData<Boolean>()
    val searchInProgress: LiveData<Boolean>
        get() = _searchInProgress

    fun search() {
        viewModelScope.launch {
            _searchInProgress.value = true
            val searchResponse: SearchResponse? = withContext(Dispatchers.IO) {
                NetworkService.siegeApi.search(name = searchString.value!!)
            }
            _searchResults.value = searchResponse?.results
            _searchInProgress.value = false
        }
    }
}