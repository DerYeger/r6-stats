package eu.yeger.r6_stats.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import eu.yeger.r6_stats.databinding.StatsFragmentBinding

class StatsFragment : Fragment() {

    private lateinit var viewModel: StatsViewModel

    private lateinit var binding: StatsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val playerId = StatsFragmentArgs.fromBundle(arguments!!).playerId
        val viewModelFactory = StatsViewModel.Factory(playerId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StatsViewModel::class.java)
        binding = StatsFragmentBinding.inflate(inflater).apply {
            viewModel = this@StatsFragment.viewModel
            lifecycleOwner = this@StatsFragment
        }
        return binding.root
    }
}