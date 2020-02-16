package eu.yeger.r6_stats.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import eu.yeger.r6_stats.databinding.SearchFragmentBinding
import eu.yeger.r6_stats.ui.OnClickListener

class SearchFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.searchExceptionAction.observe(this, Observer { exception ->
            if (exception != null) {
                Toast.makeText(this.context, exception, Toast.LENGTH_SHORT).show()
                viewModel.onSearchExceptionActionCompleted()
            }
        })
        binding = SearchFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.searchResultList.adapter = SearchResultAdapter(OnClickListener { searchResult ->
            val action = SearchFragmentDirections.actionNavigationSearchToNavigationStats()
            action.playerId = searchResult.id
            findNavController().navigate(action)
        })
        binding.platformSpinner.onItemSelectedListener = this
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
