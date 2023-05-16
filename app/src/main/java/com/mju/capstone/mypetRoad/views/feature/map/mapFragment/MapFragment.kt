package com.mju.capstone.mypetRoad.views.feature.map.mapFragment

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.databinding.FragmentMapBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.domain.model.GpsModel
import com.mju.capstone.mypetRoad.views.MainActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {
    override fun getViewBinding() = FragmentMapBinding.inflate(layoutInflater)

    private val mapViewModel by viewModels<MapViewModel>()

    // CoroutineScope -> Coroutine 관리, 조정 interface
    // 예를 들어, Activity, Fragment 생명 주기 관련 Coroutine 실행 하는데 사용
    // 이를 통해 UI 작업 비 동기적 처리, 스레드 관리와 관련된 일반적 문제 해결
    // CoroutineScope -> 'launch', 'async' 함수 사용해 Coroutine 생성, 실행에 사용
    // uiScope 통해 생성된 Coroutine -> uiScope 속한 생명 주기에 따라 자동 관리
    private lateinit var  uiScope: CoroutineScope
    private lateinit var mapView: MapView
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체
    private val marker = Marker()
    private val marker1 = Marker()
    private val marker2 = Marker()

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity
    }

    lateinit var naverMap: NaverMap

    override fun initState(){
        super.initState()
        uiScope = CoroutineScope(Dispatchers.Main)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)
        mapView = binding.mapView
        mapView.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        val timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                RetrofitManager.instance.getGPS(naverMap, marker2);
            }
        }, 0, 1000)
    }
}