package eu.yeger.r6_stats.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import eu.yeger.r6_stats.databinding.StatsFragmentBinding
import eu.yeger.r6_stats.fromSharedPreferences
import eu.yeger.r6_stats.saveToSharedPreferences

private const val LAST_PLAYER_ID = "last_player_id"

class StatsFragment : Fragment() {

    private val viewModel: StatsViewModel by viewModels {
        val playerId = StatsFragmentArgs.fromBundle(arguments!!).playerId?.also {
            saveToSharedPreferences(
                LAST_PLAYER_ID,
                it
            )
        } ?: fromSharedPreferences(LAST_PLAYER_ID)
        StatsViewModel.Factory(activity!!.application, playerId)
    }

    private lateinit var binding: StatsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.networkExceptionAction.observe(this, Observer { exception ->
            if (exception != null) {
                Toast.makeText(this.context, exception, Toast.LENGTH_SHORT).show()
                viewModel.onNetworkExceptionActionCompleted()
                findNavController().navigateUp()
            }
        })
        binding = StatsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}
