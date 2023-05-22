package com.mju.capstone.mypetRoad.views.feature.walking

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.mju.capstone.mypetRoad.R
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentWalkingBinding
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.util.Distance
import com.mju.capstone.mypetRoad.util.Route
import com.mju.capstone.mypetRoad.views.MainActivity
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WalkingFragment : BaseFragment<FragmentWalkingBinding>() {

    private lateinit var navController: NavController
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

    private var startTime : Long = 0
    private var endTime : Long = 0
    private var durationTime : Long = 0
//    = System.currentTimeMillis()
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

    override fun initViews() {
        super.initViews()
        binding.btnWalkingStart.setOnClickListener {
            // RetrofitManager.instance.getPings(naverMap, marker, mainActivity);
            Config.isWalking = !Config.isWalking
            Log.d("Config", "Config: " + Config.isWalking)
            if(Config.isWalking){
                startTime = System.currentTimeMillis()
                binding.btnWalkingStart.text = "산책 중지"
                binding.btnWalkingStart.setBackgroundColor(Color.RED)
                //1초마다 getPings
                timer = Timer()
                timer?.scheduleAtFixedRate(object : TimerTask() {
                    override fun run() {
                        RetrofitManager.instance.getPings(naverMap);
                    }
                }, 0, 2000)
            } else {
                var roadMapName = ""
                val myDate = Date()
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                val formattedDate = sdf.format(myDate)
                val et = EditText(this.requireContext())
                et.gravity = Gravity.CENTER
                //getGPS 종료
                timer?.cancel()
                timer = null
                Route.clearPing()
                val builder = AlertDialog.Builder(this.requireContext())
                    .setTitle("산책로 이름은?")
                    .setView(et)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                            roadMapName = et.text.toString()
                            Toast.makeText(
                                this.requireContext(), "$roadMapName",
                                Toast.LENGTH_SHORT).show()
                    })
                Log.i("WalkingFrag","$roadMapName")
                builder.show()

                roadMapName = "tetRoadMap1"
                endTime = System.currentTimeMillis()
                durationTime = endTime - startTime
                binding.btnWalkingStart.text = "산책 시작"
                binding.btnWalkingStart.setBackgroundColor(Color.BLUE)
                RetrofitManager.instance.WalkingOver(durationTime, roadMapName, Distance.totalDistance, 1234, formattedDate)
                Distance.clearDistance()
            }
//            showToast("산책 시작!")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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
    }

    override fun onStop() {
        //fragment 벗어나면 요청 종료
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    private fun Sliding() {
        val slidePanel = binding.walkingSlidingFrame

        val state = slidePanel.panelState
        if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
            slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        } else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
            slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }
}