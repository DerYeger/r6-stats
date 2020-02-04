package eu.yeger.r6_stats.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import eu.yeger.r6_stats.databinding.SearchFragmentBinding
import eu.yeger.r6_stats.ui.OnClickListener

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding = SearchFragmentBinding.inflate(inflater).apply {
            viewModel = this@SearchFragment.viewModel
            lifecycleOwner = this@SearchFragment
            searchResultList.adapter = SearchResultAdapter(OnClickListener { searchResult ->
                val action =
                    SearchFragmentDirections.actionNavigationSearchToNavigationStats()
                action.playerId = searchResult.id
                findNavController().navigate(action)
            })
        }
        return binding.root
    }
}