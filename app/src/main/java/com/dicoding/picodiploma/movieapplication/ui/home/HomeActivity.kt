package com.dicoding.picodiploma.movieapplication.ui.home

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.picodiploma.movieapplication.R
import com.dicoding.picodiploma.movieapplication.databinding.ActivityHomeBinding
import com.dicoding.picodiploma.movieapplication.ui.favorite.FavoriteFragment
import com.dicoding.picodiploma.movieapplication.ui.movie.MovieFragment
import com.dicoding.picodiploma.movieapplication.ui.tvseries.TVSeriesFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var activityHomeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        activityHomeBinding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val fragment = MovieFragment()
        addFragment(fragment)

//        val navView: BottomNavigationView = activityHomeBinding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                    R.id.navigation_movie,
//                    R.id.navigation_tv_series,
//                    R.id.navigation_favorite
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        supportActionBar?.elevation = 0f
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.navigation_movie -> {
                val fragment = MovieFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_tv_series -> {
                val fragment = TVSeriesFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_favorite -> {
                val fragment = FavoriteFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
//            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment)
            .commit()
    }
}