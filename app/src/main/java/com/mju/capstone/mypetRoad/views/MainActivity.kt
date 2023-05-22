package com.mju.capstone.mypetRoad.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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



    companion object {
        const val MY_LOCATION_KEY = "MY_LOCATION_KEY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //navController
        binding.bottomNavView.setupWithNavController(navController)

    }

}