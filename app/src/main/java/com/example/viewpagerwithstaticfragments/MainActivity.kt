package com.example.viewpagerwithstaticfragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var pagerAdapter: FragmentStateAdapter? = null
    private val fragmentArrayList: ArrayList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentArrayList.add(Fragment1())
        fragmentArrayList.add(Fragment2())
        fragmentArrayList.add(Fragment3())

        pagerAdapter = ViewPagerAdapter(this, fragmentArrayList)
        viewpager.adapter = pagerAdapter
        pager_dots?.let {
            ViewPagerUtil.instance?.setupIndicator(this, viewpager,
                it, fragmentArrayList.size)
        }
        ViewPagerUtil.instance?.onBackPressed(viewpager, supportFragmentManager)
    }

    override fun onBackPressed() {
        setViewPagerBackPress()
    }

    private fun setViewPagerBackPress() {
        if (viewpager!!.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewpager.currentItem = viewpager.currentItem - 1
        }
    }

}