package net.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.SlidingTabLayout
import net.fragment.FragmentI
import net.fragment.FragmentM
import net.fragment.FragmentN
import net.fragment.FragmentS

class TabUtils {
    companion object {
        private var i: TabUtils? = null
            get() {
                field ?: run {
                    field = TabUtils()
                }
                return field
            }

        @Synchronized
        fun get(): TabUtils {
            return i!!
        }
    }

    fun initViewPager(tab: SlidingTabLayout, viewpager: ViewPager, activity: FragmentActivity) {
        val views: ArrayList<Fragment> = ArrayList()
        views.add(FragmentM())
        views.add(FragmentN())
        views.add(FragmentS())
        views.add(FragmentI())
        tab.setViewPager(viewpager, Constant.title, activity, views)
    }
}