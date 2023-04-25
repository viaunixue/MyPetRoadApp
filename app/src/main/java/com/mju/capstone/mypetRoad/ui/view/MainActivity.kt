package com.mju.capstone.mypetRoad.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupJetpackNavigation()
    }

    private fun setupJetpackNavigation() {
        // navigation controller instance 획득
        val host = supportFragmentManager
            .findFragmentById(R.id.petroad_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController

        // navigation View - navigation Controller 연결
        binding.bottomNavView.setupWithNavController(navController)

        // app 상단 Bar 설정
        appBarConfiguration = AppBarConfiguration(
            // app Bar Conriguration에 navigation 그래프를 넘겨줌
            // 현재 navigation 계층 구조에 따라서 top level destination 으로 지정
//            navController.graph
            setOf(
                R.id.fragment_home, R.id.fragment_map, R.id.fragment_walking,
                R.id.fragment_analysis, R.id.fragment_settings
            )
        )

        // navigation Controller & appBarConfiguration 연결
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}