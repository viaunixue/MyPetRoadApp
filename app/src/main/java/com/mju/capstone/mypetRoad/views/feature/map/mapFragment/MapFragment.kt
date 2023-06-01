package com.mju.capstone.mypetRoad.views.feature.map.mapFragment

import android.content.Context
import com.mju.capstone.mypetRoad.databinding.FragmentMapBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.views.MainActivity
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {
    override fun getViewBinding() = FragmentMapBinding.inflate(layoutInflater)

    // CoroutineScope -> Coroutine 관리, 조정 interface
    // 예를 들어, Activity, Fragment 생명 주기 관련 Coroutine 실행 하는데 사용
    // 이를 통해 UI 작업 비 동기적 처리, 스레드 관리와 관련된 일반적 문제 해결
    // CoroutineScope -> 'launch', 'async' 함수 사용해 Coroutine 생성, 실행에 사용
    // uiScope 통해 생성된 Coroutine -> uiScope 속한 생명 주기에 따라 자동 관리
    private lateinit var  uiScope: CoroutineScope
    private lateinit var mapView: MapView
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체
    private var timer: Timer? = null
//    private val marker = Marker()
    lateinit var naverMap: NaverMap
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Context를 액티비티로 형변환해서 할당(토큰 받아올 때 쓰임)
        mainActivity = context as MainActivity
    }

    override fun initState(){
        super.initState()
        uiScope = CoroutineScope(Dispatchers.Main)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)
        mapView = binding.mapView
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map.apply {
            uiSettings.isLocationButtonEnabled = true // 현 위치 버튼 활성화 여부
            uiSettings.isScaleBarEnabled = true // 축척 바 활성화 여부
            uiSettings.isCompassEnabled = true // 나침반 활성화 여부
            uiSettings.isZoomControlEnabled = true // 줌 컨트롤 활성화 되어 있는지 여부
            uiSettings.setLogoMargin(20, 20, 100, 2520)
            isIndoorEnabled = false // 실내 지도
            isLiteModeEnabled = false // 라이트모드
            //lightness = -0.5f // 지도 밝기
            // buildingHeight = 0.8f // 건물 높이
        }
        RetrofitManager.instance.firstMoveCamera(naverMap)



        timer = Timer()

        //1초마다 getGPS
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                RetrofitManager.instance.getGPS(naverMap, mainActivity);
            }
        }, 0, 1000)

        // 지도상에 핫스팟을 표시해줌
        RetrofitManager.instance.markHotSpot(naverMap);
    }

    override fun onStop() {
        //fragment 벗어나면 요청 종료
        super.onStop()
        timer?.cancel()
        timer = null
    }
}