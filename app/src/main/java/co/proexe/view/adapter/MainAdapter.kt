package co.proexe.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.proexe.view.ProgramFragment

class MainAdapter(
    private val fragment: Fragment,
    private val countOfTabs: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = countOfTabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProgramFragment()
            else -> ProgramFragment()
        }
    }
}
