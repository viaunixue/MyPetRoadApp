package com.mju.capstone.mypetRoad.views.feature.walking

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentWalkingDetailBinding
import com.mju.capstone.mypetRoad.util.Calories
import com.mju.capstone.mypetRoad.util.Config.durationTime
import com.mju.capstone.mypetRoad.util.Config.endTime
import com.mju.capstone.mypetRoad.util.Config.pauseTime
import com.mju.capstone.mypetRoad.util.Config.startTime
import com.mju.capstone.mypetRoad.util.Config.endDate
import com.mju.capstone.mypetRoad.util.DateFormatter
import com.mju.capstone.mypetRoad.util.Distance
import com.mju.capstone.mypetRoad.util.Route
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class WalkingDetailFragment : BaseFragment<FragmentWalkingDetailBinding>(), OnMapReadyCallback{
    override fun getViewBinding() = FragmentWalkingDetailBinding.inflate(layoutInflater)

    private val LOCATION_PERMISSION_REQUEST_CODE : Int = 1000
    private lateinit var uiScope: CoroutineScope
    private lateinit var locationSource: FusedLocationSource
    private var timer: Timer? = null
    lateinit var naverMap: NaverMap
    private lateinit var mapView: MapView
    private val walkingViewModel by viewModels<WalkingViewModel>()

    override fun initState() {
        super.initState()
        uiScope = CoroutineScope(Dispatchers.Main)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        mapView = binding.detailMapView
        mapView.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.walkingViewModel = walkingViewModel //ViewModel설정
        walkingViewModel.updateDetailWalkingText()

        return binding.root
    }

    override fun initViews() {
        super.initViews()

        binding.walkingDetailBtn.setOnClickListener {
            val formattedDate = DateFormatter.dateToString(endDate)!!
            val et = EditText(this.requireContext())
            et.gravity = Gravity.CENTER
            //getGPS 종료
            timer?.cancel()
            timer = null
            Route.clearPing()

            val roadMapName = binding.detailRoadName.text.toString()

            durationTime = endTime - startTime / 1000
            RetrofitManager.instance.WalkingOver(durationTime, roadMapName, Distance.totalDistance, Calories.totalCalories, formattedDate)
            Distance.clearDistance() // 거리 변수 초기화
            Calories.clearCalories() // 칼로리 변수 초기화

            initializeTimeValue()

//            view?.let { walkingMode ->
//                Navigation.findNavController(walkingMode)
//                    .navigate(R.id.action_walkingDetailFragment_to_walkingHomeFragment)
//            }
            val navController = findNavController()
            val graph = navController.navInflater.inflate(R.navigation.petroad_nav_graph)
            navController.graph = graph
//            navController.navigate(R.id.walking) <- 홈 화면 못넘어가는 오류 발생
        }
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map.apply {
            uiSettings.isLocationButtonEnabled = false
            uiSettings.isScaleBarEnabled = false
            uiSettings.isCompassEnabled = false
            uiSettings.isZoomControlEnabled = false
//            uiSettings.setLogoMargin(20, 20, 100, 1740)
            isIndoorEnabled = false // 실내 지도
            isLiteModeEnabled = false // 라이트모드
            lightness = -0.5f // 지도 밝기
            buildingHeight = 0.8f // 건물 높이
        }
    }

    fun initializeTimeValue() {
        startTime = 0
        endTime = 0
        pauseTime = 0
        durationTime = 0
    }
}