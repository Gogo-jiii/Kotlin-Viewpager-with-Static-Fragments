package com.example.viewpagerwithstaticfragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

internal class ViewPagerAdapter(
    mainActivity: MainActivity?,
    private val fragmentArrayList: ArrayList<Fragment>
) : FragmentStateAdapter(
    mainActivity!!
) {
    override fun createFragment(position: Int): Fragment {
        return fragmentArrayList[position]
    }

    override fun getItemCount(): Int {
        return fragmentArrayList.size
    }
}