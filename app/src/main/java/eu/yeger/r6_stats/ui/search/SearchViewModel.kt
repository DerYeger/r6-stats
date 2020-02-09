package eu.yeger.r6_stats.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.yeger.r6_stats.repository.SearchRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel : ViewModel() {

    private val searchRepository = SearchRepository()

    val searchString = MutableLiveData<String>()
    val searchResults = searchRepository.searchResults

    private lateinit var selectedPlatform: String

    private val _searchInProgress = MutableLiveData<Boolean>()
    val searchInProgress: LiveData<Boolean> = _searchInProgress

    fun search() {
        Timber.i("Searching $selectedPlatform for ${searchString.value}")
        searchString.value?.let {
            viewModelScope.launch {
                _searchInProgress.value = true
                searchRepository.search(platform = selectedPlatform, name = it)
                _searchInProgress.value = false
            }
        }
    }

    fun onPlatformSelected(selectedPlatform: String) {
        this.selectedPlatform = when (selectedPlatform) {
            "Xbox" -> "xbl"
            "PS4" -> "psn"
            else -> "uplay"
        }
    }
}
