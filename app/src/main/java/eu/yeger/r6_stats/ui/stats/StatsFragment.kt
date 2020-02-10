package eu.yeger.r6_stats.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        val playerId = StatsFragmentArgs.fromBundle(arguments!!).playerId?.also {
            saveToSharedPreferences(
                LAST_PLAYER_ID,
                it
            )
        } ?: fromSharedPreferences(LAST_PLAYER_ID)
        val viewModelFactory = StatsViewModel.Factory(activity!!.application, playerId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(StatsViewModel::class.java)
        viewModel.networkExceptionAction.observe(this, Observer { exception ->
            if (exception != null) {
                Toast.makeText(this.context, exception, Toast.LENGTH_SHORT).show()
                viewModel.onNetworkExceptionActionCompleted()
                findNavController().navigateUp()
            }
        })
        binding = StatsFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}