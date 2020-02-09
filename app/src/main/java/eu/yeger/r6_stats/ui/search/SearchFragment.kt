package eu.yeger.r6_stats.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import eu.yeger.r6_stats.R
import eu.yeger.r6_stats.databinding.SearchFragmentBinding
import eu.yeger.r6_stats.ui.OnClickListener

class SearchFragment : Fragment(), AdapterView.OnItemSelectedListener {

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
            platformSpinner.onItemSelectedListener = this@SearchFragment
        }
        return binding.root
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        binding.platformSpinner.setSelection(0)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem: String = binding.platformSpinner.getItemAtPosition(position) as String
        viewModel.onPlatformSelected(selectedItem)
    }
}