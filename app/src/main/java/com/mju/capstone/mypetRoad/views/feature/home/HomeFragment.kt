package com.mju.capstone.mypetRoad.views.feature.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentHomeBinding
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.views.MainActivity
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.mju.capstone.mypetRoad.views.base.UiState
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnMapReadyCallback {
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
    lateinit var naverMap: NaverMap
    lateinit var mainActivity: MainActivity
    private lateinit var  uiScope: CoroutineScope
    private lateinit var mapView: MapView
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Context를 액티비티로 형변환해서 할당(토큰 받아올 때 쓰임)
        mainActivity = context as MainActivity
    }

    override fun initState(){
        super.initState()
        uiScope = CoroutineScope(Dispatchers.Main)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)
        mapView = binding.homeMapView
        mapView.getMapAsync(this)
    }

    override fun initViews() {
        super.initViews()
        binding.petCard.homeViewModel = homeViewModel
        homeViewModel.petInfoUpdateText()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map.apply {
            uiSettings.isLocationButtonEnabled = false
            uiSettings.isScaleBarEnabled = false
            uiSettings.isCompassEnabled = false
            uiSettings.isZoomControlEnabled = false
            uiSettings.setLogoMargin(20, 20, 100, 1520)
            isIndoorEnabled = false // 실내 지도
            isLiteModeEnabled = false // 라이트모드
            // lightness = -0.5f // 지도 밝기
            // buildingHeight = 0.8f // 건물 높이
        }
        RetrofitManager.instance.getLastestWalk(naverMap)
//        letsBinding()
    }

//    private fun letsBinding() {
//        val petNameValue = "이름 : ${Config.pet.name}"
//        val petAgeValue = "나이 : ${Config.pet.age}"
//
//        binding.petName = petNameValue
//        binding.petAge = petAgeValue
//        if(Config.todayIsWalked)
//            Config.todayIsWalkedString.set("오늘 산책 함")
//        binding.petIsWalked = Config.todayIsWalkedString.get()
//    }
}