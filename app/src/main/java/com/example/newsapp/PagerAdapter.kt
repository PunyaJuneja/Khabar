package com.example.memechat


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsapp.*

class Pageradapter(fm:FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 5;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return generalfragment()
            }
            1 -> {
                return environmentfragment()
            }
            2 -> {
                return sportsfragment()
            }
            3 -> {
                return healthfragment()
            }
            4 -> {
                return businessfragment()
            }
            else -> {
                return environmentfragment()
            }
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "GENERAL"
            }
            1 -> {
                return "SCIENCE"
            }
            2 -> {
                return "SPORTS"
            }
            3 -> {
                return "HEALTH"
            }
            4 -> {
                return "BUSINESS"
            }
        }
        return super.getPageTitle(position)
    }



}