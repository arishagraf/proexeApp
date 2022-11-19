package co.proexe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import co.proexe.R
import co.proexe.databinding.FragmentProgramBinding
import co.proexe.utils.Utils.showTost
import co.proexe.utils.mvi.IView
import co.proexe.view.adapter.ProgramAdapter
import co.proexe.view.adapter.listener.ItemClickListener
import co.proexe.view.mvi.ProgramIntent
import co.proexe.view.mvi.ProgramState
import co.proexe.view.mvi.ProgramViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProgramFragment : Fragment(), ItemClickListener, IView<ProgramState> {

    private var _binding: FragmentProgramBinding? = null
    private val binding get() = _binding!!

    private val adapter = ProgramAdapter(this)

    private val viewModel: ProgramViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.programRecyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    override fun render(state: ProgramState) {
        with(state) {
            when (programViewState) {
                is ProgramViewState.Initiating -> {}
                is ProgramViewState.ProgramsReceived -> {
                    adapter.submitList(programViewState.programList)
                }
                is ProgramViewState.OptionsSelected -> showTost(getString(R.string.opt_clicked))
                is ProgramViewState.MovieClicked -> showTost(getString(R.string.item_clicked))
            }
        }
    }

    override fun onItemOptionsClicked(isSelected: Boolean) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.intents.send(ProgramIntent.OptionsSelected)
        }
    }

    override fun onItemClicked(isClicked: Boolean) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.intents.send(ProgramIntent.MovieClicked)
        }
    }

    override fun onDestroyView() {
        binding.programRecyclerView.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
