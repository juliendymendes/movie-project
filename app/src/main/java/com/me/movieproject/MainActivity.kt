package com.me.movieproject

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import com.me.movieproject.databinding.ActivityMainBinding
import com.me.movieproject.view.ListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout = binding.drawerLayout
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.app_name, R.string.movie_poster)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener {
            var fragment : Fragment? = null
            when(it.itemId){
                R.id.nav_home -> {
                    fragment = ListFragment()
                }
            }
            if (fragment != null) {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.show(R.id.listFragment)
                ft.commit()
            }

            true
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }else super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}