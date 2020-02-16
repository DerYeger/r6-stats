package eu.yeger.r6_stats.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import eu.yeger.r6_stats.databinding.FavoritesFragmentBinding
import eu.yeger.r6_stats.ui.OnClickListener

class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels { FavoritesViewModel.Factory(this.activity!!.application) }

    private lateinit var binding: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoritesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.favoritesList.adapter = FavoriteListAdapter(OnClickListener { player ->
            val action = FavoritesFragmentDirections.actionNavigationFavoritesToNavigationStats()
            action.playerId = player.id
            findNavController().navigate(action)
        })
        return binding.root
    }
}
