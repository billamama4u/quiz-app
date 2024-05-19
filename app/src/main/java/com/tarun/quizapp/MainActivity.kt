package com.tarun.quizapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import categoryholder
import com.google.android.material.navigation.NavigationView

import com.tarun.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: categoryholder
    private val categoryData = mutableListOf<String>() // Changed to String type

    // Countries array
    private lateinit var countries: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.content_frame,
                HomeFragment()).commit()
            binding.navView.setCheckedItem(R.id.nav_home)
        }

        // Initialize RecyclerView and Adapter
        categoryAdapter = categoryholder(this,categoryData)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = categoryAdapter

        // Fetch countries from resources
        countries = resources.getStringArray(R.array.categories)
        categoryData.addAll(countries)
        categoryAdapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.content_frame,
                    HomeFragment()
                ).commit()
            }
            R.id.nav_about_us -> {
                supportFragmentManager.beginTransaction().replace(R.id.content_frame,
                    AboutUsFragment()
                ).commit()
            }
            R.id.nav_log_out -> {
                // Handle log out action, for example:
                // Snackbar.make(binding.drawerLayout, "Logging out...", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
