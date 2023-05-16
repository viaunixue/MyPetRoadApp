package com.mju.capstone.mypetRoad.views.feature.walking

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentWalkingBinding
import com.mju.capstone.mypetRoad.views.MainActivity
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalCoroutinesApi
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
    private var timer: Timer? = null
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Context를 액티비티로 형변환해서 할당(토큰 받아올 때 쓰임)
        mainActivity = context as MainActivity
    }

    override fun initState() {
        super.initState()
        uiScope = CoroutineScope(Dispatchers.Main)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        mapView = binding.walkingMapView
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map.apply {
            uiSettings.isLocationButtonEnabled = true
            uiSettings.isScaleBarEnabled = true
            uiSettings.isCompassEnabled = true
            isIndoorEnabled = false // 실내 지도
            isLiteModeEnabled = false // 라이트모드
            lightness = 0.3f // 지도 밝기
            buildingHeight = 0.5f // 건물 높이
        }
        timer = Timer()

        binding.lifecycleOwner = this

        binding.btnWalkingStart.setOnClickListener {
            walkingViewModel.onButtonClick()
        }

        //1초마다 getPings
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                RetrofitManager.instance.getPings(naverMap, marker, mainActivity);
            }
        }, 0, 1000)
    }

    override fun onStop() {
        //fragment 벗어나면 요청 종료
        super.onStop()
        timer?.cancel()
        timer = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}