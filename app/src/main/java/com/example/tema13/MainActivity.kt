package com.example.tema13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottom = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottom.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    true
                }
                R.id.action_buscar -> {
                    goToFragment(SearchFragment())
                    true
                }
                R.id.action_biblioteca -> {
                    true
                }
                R.id.action_premium -> {
                    true
                }
                else -> false
            }
        }
        bottom.selectedItemId = R.id.action_home
    }
    fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
    }
}

