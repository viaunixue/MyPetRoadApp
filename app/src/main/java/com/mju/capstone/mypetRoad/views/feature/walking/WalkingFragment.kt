package com.mju.capstone.mypetRoad.views.feature.walking

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentWalkingBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class WalkingFragment : BaseFragment<FragmentWalkingBinding>(), OnMapReadyCallback {

    override fun getViewBinding() =  FragmentWalkingBinding.inflate(layoutInflater)

    private val LOCATION_PERMISSION_REQUEST_CODE : Int = 1000

    private val walkingViewModel by viewModels<WalkingViewModel>()

    private lateinit var uiScope: CoroutineScope
    private lateinit var locationSource: FusedLocationSource
    lateinit var naverMap: NaverMap
    private lateinit var mapView: MapView
    private val marker = Marker()

    override fun initState() {
        super.initState()
        uiScope = CoroutineScope(Dispatchers.Main)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        mapView = binding.walkingMapView
        mapView.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

//        naverMap.isIndoorEnabled = false // 실내 지도
//        naverMap.isLiteModeEnabled = false // 라이트 모드
//        naverMap.lightness = 0.3f // 지도 밝기
//        naverMap.buildingHeight = 0.5f // 건물 높이


        RetrofitManager.instance.getPings(naverMap, marker);
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}