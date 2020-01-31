package eu.yeger.r6_stats.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.yeger.r6_stats.domain.SearchResult
import eu.yeger.r6_stats.ui.search.SearchResultAdapter

@BindingAdapter("searchResults")
fun RecyclerView.bindSearchResults(searchResults: List<SearchResult>?) {
    val adapter = adapter as SearchResultAdapter
    adapter.submitList(searchResults)
}