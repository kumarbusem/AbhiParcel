package com.sendfast.abhiparcel.activities

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.sendfast.abhiparcel.R
import com.sendfast.abhiparcel.fragments.*
import com.sendfast.abhiparcel.fragments.FragmentHome


class MainActivity : AppCompatActivity() {
    val fm = fragmentManager
    val fragmentTransaction = fm.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        navView.menu.findItem(R.id.navigation_home).icon.setColorFilter(Color.parseColor("#D51A1A"), PorterDuff.Mode.SRC_IN)
        navView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragmentHomeClick()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_orders -> {
                    fragmentHistoryClick()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_menu -> {
                    fragmentMenuClick()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_help -> {
                    fragmentHelpClick()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    fragmentProfileClick()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        Log.e("CLICK:   ", "Activity Before: " + fm.backStackEntryCount)
        fragmentHomeClick()
        Log.e("CLICK:   ", "Activity After: " + fm.backStackEntryCount)
    }

    private fun fragmentProfileClick() {
        var myfragmentProfile: Fragment = FragmentProfile()
        if (fm.backStackEntryCount == 1) {
            fm.popBackStack()
        }
        fragmentTransaction.add(R.id.fragment_switch, myfragmentProfile)
        fragmentTransaction.addToBackStack("profile")
        fragmentTransaction.commit()
        Log.e("CLICK:   ", "Profile: " + fm.backStackEntryCount)
    }

    private fun fragmentHelpClick() {
        var myfragmentHelp: Fragment = FragmentHelp()
        if (fm.backStackEntryCount == 1) {
            fm.popBackStack()
        }
        fragmentTransaction.add(R.id.fragment_switch, myfragmentHelp)
        fragmentTransaction.addToBackStack("offers")
        fragmentTransaction.commit()
        Log.e("CLICK:   ", "Help: " + fm.backStackEntryCount)
    }

    private fun fragmentMenuClick() {
        var myfragmentMenu: Fragment = FragmentMenu()
        if (fm.backStackEntryCount == 1) {
            fm.popBackStack()
        }
        fragmentTransaction.add(R.id.fragment_switch, myfragmentMenu)
        fragmentTransaction.addToBackStack("menu")
        fragmentTransaction.commit()
        Log.e("CLICK:   ", "Menu: " + fm.backStackEntryCount)
    }

    private fun fragmentHistoryClick() {
        var myfragmentHistory: Fragment = FragmentHistory()
        if (fm.backStackEntryCount == 1) {
            fm.popBackStack()
        }
        fragmentTransaction.add(R.id.fragment_switch, myfragmentHistory)
        fragmentTransaction.addToBackStack("history")
        fragmentTransaction.commit()
        Log.e("CLICK:   ", "History: " + fm.backStackEntryCount)
    }

    private fun fragmentHomeClick() {
        var myfragmentHome: Fragment = FragmentHome()
        Log.e("CLICK:   ", "Home function: " + fm.backStackEntryCount)
        if (fm.backStackEntryCount == 0){
            fragmentTransaction.replace(R.id.fragment_switch, myfragmentHome)
            fragmentTransaction.addToBackStack("home")
            fragmentTransaction.commit()
            Log.e("CLICK:   ", "Home Added: " + fm.backStackEntryCount)
        }
        else if (fm.backStackEntryCount == 1){
            fm.popBackStack()
            Log.e("CLICK:   ", "Home Popped: " + fm.backStackEntryCount)
        }


    }


}
