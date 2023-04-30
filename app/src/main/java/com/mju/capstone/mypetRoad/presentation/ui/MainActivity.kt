package com.mju.capstone.mypetRoad.presentation.ui

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navControllerProvider: Provider<NavController>
    // fragment 가 생성이 되고 fragment 가 있어야 navController 가
    // 그때 그 id 값을 반환 하면서 get 으로 가져옴
    private val navController get() = navControllerProvider.get()

//    private lateinit var myLocationListener: MyLocationListener

//    companion object {
//        private const val MY_LOCATION_KEY = "MY_LOCATION_KEY"
//
//        val PERMISSIONS = arrayOf(
//            android.Manifest.permission.ACCESS_FINE_LOCATION,
//            android.Manifest.permission.ACCESS_COARSE_LOCATION)
//    }


//    private val permissionLauncher =
//        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
//        { permissions ->
//            val responsePermissions = permissions.entries.filter {
//                it.key in PERMISSIONS
//            }
//            if(responsePermissions.filter { it.value == true }.size == PERMISSIONS.size) {
//                setLocationListener()
//            } else {
//                Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        permissionLauncher.launch(PERMISSIONS)

        //navController
        binding.bottomNavView.setupWithNavController(navController)

    }


//    private fun observeData() = with(binding) {
//
//    }
//
//    @Suppress("MissingPermission")
//    private fun setLocationListener() {
//        val minTime: Long = 1000
//        val minDistance = 1f
//
//        if(::myLocationListener.isInitialized.not()){
//
//        }
//    }
//
//    inner class MyLocationListener : LocationListener {
//        @RequiresApi(Build.VERSION_CODES.P)
//        override fun onLocationChanged(location: Location) {
//
//        }
//
//
//    }
}