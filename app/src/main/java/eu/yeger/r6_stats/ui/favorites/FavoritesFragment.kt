package eu.yeger.r6_stats.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import eu.yeger.r6_stats.databinding.FavoritesFragmentBinding

class FavoritesFragment : Fragment() {

    private lateinit var viewModel: FavoritesViewModel

    private lateinit var binding: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModelFactory = FavoritesViewModel.Factory(activity!!.application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)
        binding = FavoritesFragmentBinding.inflate(inflater).apply {
            viewModel = this@FavoritesFragment.viewModel
            lifecycleOwner = this@FavoritesFragment
        }
        return binding.root
    }
}
