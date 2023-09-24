package com.example.careurplant.adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.careurplant.myPostFragment

class viewPagerAdapter(fm:FragmentManager): FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    var fragmentList= mutableListOf<Fragment>()
    var titleList= mutableListOf<String>()
    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList.get(position)
    }
    fun addFragments(fragment: myPostFragment.Companion, title:String){
       // fragmentList.add(fragment) needd to be update
        titleList.add(title)
    }
}