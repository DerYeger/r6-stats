package eu.yeger.r6_stats.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import eu.yeger.r6_stats.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding = SearchFragmentBinding.inflate(inflater).apply {
            viewModel = searchViewModel
            lifecycleOwner = this@SearchFragment
            searchResultList.adapter = SearchResultAdapter()
        }
        return binding.root
    }
}