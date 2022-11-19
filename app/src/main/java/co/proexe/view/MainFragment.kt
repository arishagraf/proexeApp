package co.proexe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import co.proexe.databinding.FragmentMainBinding
import co.proexe.utils.mvi.IView
import co.proexe.view.adapter.MainAdapter
import co.proexe.view.mvi.MainIntent
import co.proexe.view.mvi.MainState
import co.proexe.view.mvi.MainViewState
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(), IView<MainState> {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //  private lateinit var tabLayoutMediator: TabLayoutMediator

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            render(it)
        }

        lifecycleScope.launch {
            viewModel.intents.send(MainIntent.SetUpTabLayout)
        }
    }

    override fun render(state: MainState) {
        when (state.mainViewState) {
            is MainViewState.Initiating -> {}
            is MainViewState.SetUpTabs -> {
                binding.moviesViewPager.adapter =
                    MainAdapter(this, state.mainViewState.category.size)
                val tabLayoutMediator =
                    TabLayoutMediator(binding.tabLayout, binding.moviesViewPager) { tab, position ->
                        when (position) {
                            0 -> tab.text = state.mainViewState.category[position]
                            else -> tab.text = state.mainViewState.category[position]
                        }
                    }
                tabLayoutMediator.attach()
            }
        }
    }

    override fun onDestroyView() {
        binding.moviesRecyclerView.adapter = null
        binding.moviesViewPager.adapter = null
        super.onDestroyView()
        _binding = null
    }
}