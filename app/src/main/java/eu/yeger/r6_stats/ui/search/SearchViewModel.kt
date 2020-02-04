package eu.yeger.r6_stats.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.yeger.r6_stats.domain.SearchResponse
import eu.yeger.r6_stats.domain.SearchResult
import eu.yeger.r6_stats.network.NetworkService
import eu.yeger.r6_stats.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SearchViewModel : ViewModel() {

    private val searchRepository = SearchRepository()

    val searchString = MutableLiveData<String>()
    val searchResults = searchRepository.searchResults

    private val _searchInProgress = MutableLiveData<Boolean>()
    val searchInProgress: LiveData<Boolean> = _searchInProgress

    fun search() {
        Timber.i("Searching: ${searchString.value}")
//        searchString.value?.let {
            viewModelScope.launch {
                _searchInProgress.value = true
                searchRepository.search(searchString.value!!)
                _searchInProgress.value = false
            }
//        }
    }
}
