package com.mju.capstone.mypetRoad.views.feature.map.mapFragment

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitInstance
//import com.mju.capstone.mypetRoad.data.db.MapDB
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

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {
    override fun getViewBinding() = FragmentMapBinding.inflate(layoutInflater)

    private val mapViewModel by viewModels<MapViewModel>()

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

//        var retrofitInstance = RetrofitInstance.trackerService
////        var temp: GpsModel?
//
//        retrofitInstance.getGps().enqueue(object : Callback<GpsModel> {
//            override fun onResponse(call: Call<GpsModel>, response: Response<GpsModel>) {
//                if(response.isSuccessful){
//                    var result: GpsModel? = response.body()
//                    if (result != null) {
//                        marker2.position = LatLng(result.latitude, result.longitude,)
//                        marker2.map = naverMap // 고씨네
//                        marker2.captionText = "GPS 위치마커"
//                    }
//                    Log.d("YJ", "onResponce 성공: " + result?.toString());
//                }
//                else{
//                    Log.d("YJ", "onResponce 실패")
//                }
//            }
//            override fun onFailure(call: Call<GpsModel>, t: Throwable) {
//                Log.d("YJ", "네트워크 에러 : " + t.message.toString())
//            }
//        })
        RetrofitManager.instance.getGPS(naverMap, marker, mainActivity)

        // 현재 위치 마커
        marker.position = LatLng(37.6281, 127.0905)
        marker.map = naverMap
        marker.icon = MarkerIcons.BLACK
        marker.iconTintColor = Color.RED // 현재위치 마커 빨간색으로

        // 장소 리스트 마커
        marker1.position = LatLng(37.62444907132257, 127.09321109051345)
        marker1.map = naverMap
        marker1.captionText = "화랑대 철도공원"
    }
}