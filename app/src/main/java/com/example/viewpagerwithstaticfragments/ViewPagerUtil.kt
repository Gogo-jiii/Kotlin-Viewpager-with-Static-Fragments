package com.example.viewpagerwithstaticfragments

import android.app.Activity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

class ViewPagerUtil private constructor() {
    private lateinit var ivArrayDotsPager: Array<ImageView?>

    companion object {
        var instance: ViewPagerUtil? = null
            get() {
                if (field == null) {
                    field = ViewPagerUtil()
                }
                return field
            }
            private set
    }

    fun setupIndicator(
        activity: Activity?, viewPager2: ViewPager2, pagerDots: LinearLayout,
        size: Int
    ) {
        ivArrayDotsPager = arrayOfNulls(size)
        for (i in 0 until size) {
            //create an indicator dot
            ivArrayDotsPager[i] = ImageView(activity)
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(25, 0, 25, 0)
            ivArrayDotsPager[i]!!.layoutParams = params
            ivArrayDotsPager[i]!!.setImageResource(R.drawable.viewpager_indicator_unselected)
            ivArrayDotsPager[i]!!.setOnClickListener { view -> view.alpha = 1f }

            //add that indicator to the layout in the xml
            pagerDots.addView(ivArrayDotsPager[i])
            pagerDots.bringToFront()
        }

        //set current indicator as selected
        ivArrayDotsPager[0]!!.setImageResource(R.drawable.viewpager_indicator_selected)
        val onPageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in ivArrayDotsPager.indices) {
                    ivArrayDotsPager[i]!!.setImageResource(R.drawable.viewpager_indicator_unselected)
                }
                ivArrayDotsPager[position]!!.setImageResource(R.drawable.viewpager_indicator_selected)
            }

        }
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback)
    }

    fun onBackPressed(viewPager2: ViewPager2, fragmentManager: FragmentManager) {
        viewPager2.isFocusableInTouchMode = true
        viewPager2.requestFocus()
        viewPager2.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (viewPager2.currentItem == 0) {
                    fragmentManager.popBackStack()
                } else {
                    viewPager2.setCurrentItem(0, true)
                    viewPager2.isFocusableInTouchMode = false
                    viewPager2.clearFocus()
                }
                return@OnKeyListener true
            }
            false
        })
    }
}