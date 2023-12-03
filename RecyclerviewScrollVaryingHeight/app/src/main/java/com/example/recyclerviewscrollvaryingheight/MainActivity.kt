package com.example.recyclerviewscrollvaryingheight

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = CoolPagerAdapter(supportFragmentManager)
        val tabLayout: TabLayout = findViewById(R.id.pager_header)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Default"
                1 -> "No Smooth Scroll"
                2 -> "Smooth Scroll"
                3 -> "Custom LayoutManager"
                else -> ""
            }
        }.attach()
    }

    inner class CoolPagerAdapter(fm: FragmentManager) : FragmentStateAdapter(fm, lifecycle) {

        override fun getItemCount(): Int {
            return 4
        }

        override fun createFragment(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("position", position)
            val fragment = CoolFragment()
            fragment.arguments = bundle
            return fragment
        }

    }
}
