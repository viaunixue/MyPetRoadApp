package com.mju.capstone.mypetRoad.views.feature.walking

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentWalkingStartBinding
import com.mju.capstone.mypetRoad.util.Calories
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.util.Config.durationTime
import com.mju.capstone.mypetRoad.util.Config.endTime
import com.mju.capstone.mypetRoad.util.Config.pauseTime
import com.mju.capstone.mypetRoad.util.Config.startTime
import com.mju.capstone.mypetRoad.util.Distance
import com.mju.capstone.mypetRoad.util.Route
import com.mju.capstone.mypetRoad.views.MainActivity
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WalkingStartFragment : BaseFragment<FragmentWalkingStartBinding>(), OnMapReadyCallback {

    override fun getViewBinding() = FragmentWalkingStartBinding.inflate(layoutInflater)

    private val LOCATION_PERMISSION_REQUEST_CODE : Int = 1000

    private lateinit var uiScope: CoroutineScope
    private lateinit var locationSource: FusedLocationSource
    lateinit var naverMap: NaverMap
    private lateinit var mapView: MapView
    private var timer: Timer? = null
    lateinit var mainActivity: MainActivity

//    private var startTime : Long = 0
//    private var endTime : Long = 0
//    private var pauseTime : Long = 0
//    private var durationTime : Long = 0

    private var isPanelExpanded = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startTime = System.currentTimeMillis()
        // Context를 액티비티로 형변환해서 할당(토큰 받아올 때 쓰임)
        mainActivity = context as MainActivity
    }

    override fun initState() {
        super.initState()
        uiScope = CoroutineScope(Dispatchers.Main)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        mapView = binding.walkingStartMapView
        mapView.getMapAsync(this)
    }

    override fun initViews() {
        super.initViews()
        Log.d("Config", "Config: " + Config.isWalking)
        startTime = System.currentTimeMillis()

        binding.btnWalkingStop.setOnClickListener {
            pauseTime = System.currentTimeMillis()
            Config.isWalking = false
            Log.d("Config", "Config: " + Config.isWalking)
            binding.btnWalkingStop.visibility = View.INVISIBLE
            binding.btnWalkingRestart.visibility = View.VISIBLE
        }

        binding.btnWalkingRestart.setOnClickListener {
            val pauseStartTime = System.currentTimeMillis() - pauseTime
            startTime += pauseStartTime
            Config.isWalking = true
            binding.btnWalkingStop.visibility = View.VISIBLE
            binding.btnWalkingRestart.visibility = View.INVISIBLE
        }

        binding.btnWalkingEnd.setOnClickListener {
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
            durationTime = endTime - startTime / 1000
            RetrofitManager.instance.WalkingOver(durationTime, roadMapName, Distance.totalDistance, Calories.totalCalories, formattedDate)
            Distance.clearDistance()

            Sliding()
        }

        binding.walkingEndNo.setOnClickListener {
            Sliding()
        }

        binding.walkingEndYes.setOnClickListener {
            view?.let { walkingMode ->
                Navigation.findNavController(walkingMode)
                    .navigate(R.id.action_walkingStartFragment_to_walkingDetailFragment)
            }
        }


    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map.apply {
            uiSettings.isLocationButtonEnabled = true
            uiSettings.isScaleBarEnabled = true
            uiSettings.isCompassEnabled = true
            uiSettings.isZoomControlEnabled = true
//            uiSettings.setLogoMargin(20, 20, 100, 1740)
            isIndoorEnabled = false // 실내 지도
            isLiteModeEnabled = false // 라이트모드
            //lightness = -0.5f // 지도 밝기
            // buildingHeight = 0.8f // 건물 높이
        }

        timer = Timer()

        //1초마다 getGPS
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if(Config.isWalking) RetrofitManager.instance.getPings(naverMap);
            }
        }, 0, 1000)
    }

    override fun onStop() {
        //fragment 벗어나면 요청 종료
        super.onStop()
        timer?.cancel()
        timer = null
    }

    private fun Sliding() {
        val slidePanel = binding.walkingSlidingFrame
        val state = slidePanel.panelState

        slidePanel.isTouchEnabled = false

        if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
            slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        } else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
            slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}