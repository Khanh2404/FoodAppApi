package com.example.foodapp.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.foodapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liên kết DrawerLayout, NavigationView và BottomNavigationView từ layout
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.btnnavigation)

        // Thiết lập toolbar

        // Thiết lập ActionBarDrawerToggle để kết nối toolbar với DrawerLayout
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Thiết lập NavHostFragment và NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.framentContainer) as NavHostFragment
        navController = navHostFragment.navController

        // Liên kết NavigationView và BottomNavigationView với NavController
        navigationView.setupWithNavController(navController)
        bottomNavigationView.setupWithNavController(navController)
    }
    fun openDrawer() {
        drawerLayout.open()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
