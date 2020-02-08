package eu.yeger.r6_stats.ui

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
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

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?) {
    imageUrl?.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .into(this)
    }
}

@BindingAdapter("ubiUserId")
fun ImageView.bind(userId: String?) {
    bindImage("https://ubisoft-avatars.akamaized.net/${userId}/default_146_146.png")
}
