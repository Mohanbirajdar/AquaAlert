package com.example.careurplant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView



class HomePage : AppCompatActivity() {
   private lateinit var frameLayout: FrameLayout
   private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        frameLayout=findViewById(R.id.frame)
        bottomNavigationView=findViewById(R.id.bnView)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home->replaceFragment(homeFragment())
                R.id.profile->replaceFragment(profileFragment())
                R.id.add->replaceFragment(AddFragment())
                R.id.Search->replaceFragment(SearchFragment())
                R.id.post->replaceFragment(PostFragment())
            }
            true


        }
        replaceFragment(homeFragment())//initially load the home fragment

    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commit()
    }
}