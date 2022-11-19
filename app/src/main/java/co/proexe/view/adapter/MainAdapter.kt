package co.proexe.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.proexe.view.MainFragment
import co.proexe.view.ProgramFragment

class MainAdapter(
    private val fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProgramFragment()
            1 -> ProgramFragment()
            else -> MainFragment()
        }
    }
}
