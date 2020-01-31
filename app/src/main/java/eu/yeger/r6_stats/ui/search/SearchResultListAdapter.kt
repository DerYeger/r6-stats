package eu.yeger.r6_stats.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.yeger.r6_stats.databinding.SearchResultViewBinding
import eu.yeger.r6_stats.domain.SearchResult

class SearchResultAdapter :
    ListAdapter<SearchResult, SearchResultAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: SearchResultViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(searchResult: SearchResult, index: Int) {
            binding.searchResult = searchResult
            binding.index = index
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SearchResult>() {
        override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult) =
            oldItem.id === newItem.id

        override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SearchResultViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchResult: SearchResult = getItem(position)
        holder.bind(searchResult, position + 1)
    }
}
