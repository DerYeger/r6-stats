package eu.yeger.r6_stats.ui.search

import androidx.lifecycle.*
import eu.yeger.r6_stats.repository.SearchRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel : ViewModel() {

    private val searchRepository = SearchRepository()

    val searchString = MutableLiveData<String>()
    val searchResults = searchRepository.searchResults

    val hasResults = Transformations.map(searchResults) { results ->
        results?.size ?: 0 > 0
    }

    private lateinit var selectedPlatform: String

    private val _searchInProgress = MutableLiveData<Boolean>()
    val searchInProgress: LiveData<Boolean> = _searchInProgress

    private val _searchExceptionAction = MutableLiveData<String>()
    val searchExceptionAction: LiveData<String> = _searchExceptionAction

    private val searchExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _searchInProgress.value = false
        _searchExceptionAction.value = exception.message
    }

    fun search() {
        searchString.value?.let {
            viewModelScope.launch(searchExceptionHandler) {
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

    fun onSearchExceptionActionCompleted() {
        _searchExceptionAction.value = null
    }
}
