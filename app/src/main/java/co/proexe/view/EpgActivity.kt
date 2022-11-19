package co.proexe.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import co.proexe.R
import co.proexe.databinding.ActivityEpgBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpgActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEpgBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.navController

        setContentView(binding.root)
    }
}