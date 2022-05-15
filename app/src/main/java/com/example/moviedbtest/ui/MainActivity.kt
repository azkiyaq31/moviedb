package com.example.moviedbtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.moviedbtest.R
import com.example.moviedbtest.databinding.ActivityMainBinding
import com.example.moviedbtest.util.NavigationUtil
import com.example.moviedbtest.util.WidgetUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_fav -> {
                NavigationUtil.navigateFragment(
                    findNavController(R.id.fragment_container_content),
                    R.id.action_home_fragment_to_favorite_movie_fragment,
                    WidgetUtil.getNavOptions(
                        R.anim.enter_from_right,
                        R.anim.exit_to_right,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                )
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = findNavController(R.id.fragment_container_content)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.title = ""
            toolbar.setupWithNavController(navController, appBarConfiguration)
        }
    }
}