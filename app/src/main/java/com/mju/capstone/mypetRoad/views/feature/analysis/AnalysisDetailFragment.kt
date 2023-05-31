package com.mju.capstone.mypetRoad.views.feature.analysis

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.datastore.dataStore
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentAnalysisDetailBinding
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
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
import java.util.*
import kotlin.math.log


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AnalysisDetailFragment : BaseFragment<FragmentAnalysisDetailBinding>(), OnMapReadyCallback{

    companion object {
        private const val ARG_WALKING_LOG = "walkingLog"
        fun newInstance(walkingLog: WalkingLog): AnalysisDetailFragment {
            val fragment = AnalysisDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable("walkingLog", walkingLog)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun getViewBinding() = FragmentAnalysisDetailBinding.inflate(layoutInflater)

    private lateinit var  uiScope: CoroutineScope
    private lateinit var mapView: MapView
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체
    lateinit var naverMap: NaverMap
    private val analysisViewModel by viewModels<AnalysisViewModel>()

    override fun initState() {
        super.initState()
        uiScope = CoroutineScope(Dispatchers.Main)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)
        mapView = binding.analysisMapView
        mapView.getMapAsync(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initViews() {
        super.initViews()
        val selectedDate = requireArguments().getString("selectedDate")
        if(selectedDate != null){
            binding.analysisDetailDate.text = selectedDate
            binding.analysisViewModel = analysisViewModel //ViewModel설정
            analysisViewModel.monthlyDetailUpdateText(selectedDate)  //텍스트업뎃
        } else {
        //TODO 주간이나 월간에서 눌렀을 시
        }

        binding.detailBackBtn.setOnClickListener {
//            view?.let { analysisMode ->
//                Navigation.findNavController(analysisMode)
//                    .navigate(R.id.action_analysisDetailFragment_to_monthlyFragment2)
//            }
            val navController = findNavController()
            val graph = navController.navInflater.inflate(R.navigation.petroad_nav_graph)
            navController.graph = graph
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMapReady(map: NaverMap) {
        naverMap = map.apply {
            uiSettings.isLocationButtonEnabled = true
            uiSettings.isScaleBarEnabled = true
            uiSettings.isCompassEnabled = true
            uiSettings.isZoomControlEnabled = true
            uiSettings.setLogoMargin(20, 20, 100, 1520)
            isIndoorEnabled = false // 실내 지도
            isLiteModeEnabled = false // 라이트모드
            //lightness = -0.5f // 지도 밝기
            // buildingHeight = 0.8f // 건물 높이
        }
        //가장 최근의 산책로 그리기
        val year = binding.analysisDetailDate.text.take(4).toString()
        val month = binding.analysisDetailDate.text.substring(5..6)
        val day = binding.analysisDetailDate.text.substring(8..9)
        val processedStartTime = binding.startTime.text.toString()
            .replace("시 ", "").replace("분", "").toInt()
        val processedEndTime = binding.endTime.text.toString()
            .replace("시 ", "").replace("분", "").toInt()
        Route.setWalkLine(naverMap, year, month, day, processedStartTime, processedEndTime)
    }
}