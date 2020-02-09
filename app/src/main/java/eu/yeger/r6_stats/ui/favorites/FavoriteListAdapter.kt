package eu.yeger.r6_stats.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.yeger.r6_stats.databinding.FavoriteViewBinding
import eu.yeger.r6_stats.domain.Player
import eu.yeger.r6_stats.ui.OnClickListener

class FavoriteListAdapter(private val onClickListener: OnClickListener<Player>) : ListAdapter<Player, FavoriteListAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: FavoriteViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.player = player
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player) =
            oldItem.id === newItem.id

        override fun areContentsTheSame(oldItem: Player, newItem: Player) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FavoriteViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player: Player = getItem(position)
        holder.bind(player)
    }
}
