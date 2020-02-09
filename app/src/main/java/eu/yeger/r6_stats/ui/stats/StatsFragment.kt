package eu.yeger.r6_stats.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import eu.yeger.r6_stats.databinding.StatsFragmentBinding
import eu.yeger.r6_stats.fromSharedPreferences
import eu.yeger.r6_stats.saveToSharedPreferences

private const val LAST_PLAYER_ID = "last_player_id"

class StatsFragment : Fragment() {

    private lateinit var viewModel: StatsViewModel

    private lateinit var binding: StatsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val playerId = StatsFragmentArgs.fromBundle(arguments!!).playerId?.also { saveToSharedPreferences(LAST_PLAYER_ID, it) } ?: fromSharedPreferences(LAST_PLAYER_ID)
        val viewModelFactory = StatsViewModel.Factory(activity!!.application, playerId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StatsViewModel::class.java)
        binding = StatsFragmentBinding.inflate(inflater).apply {
            viewModel = this@StatsFragment.viewModel
            lifecycleOwner = this@StatsFragment
        }
        return binding.root
    }
}