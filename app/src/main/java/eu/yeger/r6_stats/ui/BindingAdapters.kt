package eu.yeger.r6_stats.ui

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import eu.yeger.r6_stats.domain.SearchResult
import eu.yeger.r6_stats.ui.search.SearchResultAdapter

@BindingAdapter("searchResults")
fun RecyclerView.bindSearchResults(searchResults: List<SearchResult>?) {
    val adapter = adapter as SearchResultAdapter
    adapter.submitList(searchResults)
}

@BindingAdapter("visible")
fun View.bindVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.bindSwipeRefreshLayout(listener: Runnable) {
    setOnRefreshListener {
        listener.run()
    }
}
