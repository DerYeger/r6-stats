package eu.yeger.r6_stats.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.yeger.r6_stats.repository.SearchRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val searchRepository = SearchRepository()

    val searchString = MutableLiveData<String>()
    val searchResults = searchRepository.searchResults

    private val _hasNoResults = MutableLiveData<Boolean>()
    val hasNoResults: LiveData<Boolean> = _hasNoResults

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
                _hasNoResults.value = false
                _searchInProgress.value = true
                val gotResult = searchRepository.search(platform = selectedPlatform, name = it)
                _hasNoResults.value = !gotResult
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
