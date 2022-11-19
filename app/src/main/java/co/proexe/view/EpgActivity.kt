package co.proexe.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import co.proexe.R
import co.proexe.databinding.ActivityEpgBinding
import co.proexe.utils.mvi.IView
import co.proexe.view.mvi.EpgState
import co.proexe.view.mvi.EpgViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpgActivity : FragmentActivity(), IView<EpgState> {

    private val viewModel: EpgViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEpgBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.navController

        viewModel.state.observe(this) {
            render(it)
        }

        setContentView(binding.root)
    }

    override fun render(state: EpgState) {
        when (state.epgViewState) {
            is EpgViewState.Initiating -> {}
            is EpgViewState.ProgramsReceived -> {}
        }
    }
}