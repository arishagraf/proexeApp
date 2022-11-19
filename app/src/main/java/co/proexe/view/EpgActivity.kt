package co.proexe.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import co.proexe.databinding.ActivityEpgBinding
import co.proexe.epgmvi.EPGState
import co.proexe.epgmvi.EPGViewState
import co.proexe.utils.IView

class EpgActivity : ComponentActivity(), IView<EPGState> {

    private var binding: ActivityEpgBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpgBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun render(state: EPGState) {
        when (state.epgViewState) {
            is EPGViewState.Loading -> {}
        }
    }
}