package com.mju.capstone.mypetRoad.views.feature.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentHomeBinding
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.views.MainActivity
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnMapReadyCallback {
    lateinit var naverMap: NaverMap
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Context를 액티비티로 형변환해서 할당(토큰 받아올 때 쓰임)
        mainActivity = context as MainActivity
    }
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val petNameValue = "이름 : ${Config.pet.name}"
        val petAgeValue = "나이 : ${Config.pet.age}"
        val petIsWalkedValue = if(Config.todayIsWalked){
            "오늘 산책 함"
        } else {
            "오늘 산책 안함"
        }
        binding.petName = petNameValue
        binding.petAge = petAgeValue
        binding.petIsWalked = petIsWalkedValue
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map.apply {
            uiSettings.isLocationButtonEnabled = true
            uiSettings.isScaleBarEnabled = true
            uiSettings.isCompassEnabled = true
            uiSettings.isZoomControlEnabled = true
            uiSettings.setLogoMargin(20, 20, 100, 1520)
            isIndoorEnabled = false // 실내 지도
            isLiteModeEnabled = false // 라이트모드
            // lightness = -0.5f // 지도 밝기
            // buildingHeight = 0.8f // 건물 높이
        }
        RetrofitManager.instance.getLastestWalk(naverMap, mainActivity)
    }

    private val homeViewModel by viewModels<HomeViewModel>()

}