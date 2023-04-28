package com.mju.capstone.mypetRoad.presentation.ui

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var myLocationListener: MyLocationListener

    companion object {
        private const val MY_LOCATION_KEY = "MY_LOCATION_KEY"

        val PERMISSIONS = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)
    }


    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            val responsePermissions = permissions.entries.filter {
                it.key in PERMISSIONS
            }
            if(responsePermissions.filter { it.value == true }.size == PERMISSIONS.size) {
                setLocationListener()
            } else {
                Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
            }

        }
    @SuppressLint("MissingPermission")
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

//        // app 상단 Bar 설정
//        appBarConfiguration = AppBarConfiguration(
//            // app Bar Conriguration에 navigation 그래프를 넘겨줌
//            // 현재 navigation 계층 구조에 따라서 top level destination 으로 지정
////            navController.graph
//            setOf(
//                R.id.fragment_home, R.id.fragment_map, R.id.fragment_walking,
//                R.id.fragment_analysis, R.id.fragment_settings
//            )
//        )
//
//        // navigation Controller & appBarConfiguration 연결
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun observeData() = with(binding) {

    }

    @Suppress("MissingPermission")
    private fun setLocationListener() {
        val minTime: Long = 1000
        val minDistance = 1f

        if(::myLocationListener.isInitialized.not())
    }

    inner class MyLocationListener : LocationListener {
        @RequiresApi(Build.VERSION_CODES.P)
        override fun onLocationChanged(location: Location) {

        }

        @RequiresApi(Build.VERSION_CODES.P)
        @SuppressLint("MissingPermission")
        private fun removeLocationListener() {
            if(viewmodel.getLocationManager())
        }

    }
}