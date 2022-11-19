package co.proexe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.proexe.R
import co.proexe.databinding.FragmentMainBinding
import co.proexe.view.adapter.MainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabLayoutMediator: TabLayoutMediator

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

        binding.moviesViewPager.adapter = MainAdapter(this)

        tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.moviesViewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.coming_soon)
                    1 -> tab.text = getString(R.string.coming_soon)
                }
            }
        tabLayoutMediator.attach()
    }

    override fun onDestroyView() {
        binding.moviesRecyclerView.adapter = null
        binding.moviesViewPager.adapter = null
        tabLayoutMediator.detach()
        super.onDestroyView()
        _binding = null
    }
}