package com.mju.capstone.mypetRoad.presentation.ui.map

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.data.api.RetrofitInstance
import com.mju.capstone.mypetRoad.data.url.Url
//import com.mju.capstone.mypetRoad.data.db.MapDB
import com.mju.capstone.mypetRoad.databinding.FragmentMapBinding
import com.mju.capstone.mypetRoad.domain.model.GpsModel
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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private val mapViewModel by viewModels<MapViewModel>()

//    private lateinit var database: MapDB
    private lateinit var  uiScope: CoroutineScope

    private lateinit var mapView: MapView
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체
    private val marker = Marker()
    private val marker1 = Marker()
    private val marker2 = Marker()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

//        database = MapDB.getInstance(this)!!
        uiScope = CoroutineScope(Dispatchers.Main)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        Log.e("e","Onstart")
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        Log.e("e","OnResume")
    }

    override fun onPause() {
        super.onPause()
        mapView.onResume()
        Log.e("e","OnPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        Log.e("e","OnStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
        Log.e("e","OnDest")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
        Log.e("e","OnLOw")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("e","Onview")
    }

    override fun onMapReady(naverMap: NaverMap) {
        Companion.naverMap = naverMap

        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        lateinit var retrofitInstance : RetrofitInstance

        retrofitInstance.service.getGps().enqueue(object : Callback<GpsModel>{
            override fun onResponse(call: Call<GpsModel>, response: Response<GpsModel>) {
                if(response.isSuccessful){
                    var result: GpsModel? = response.body()
                    Log.d("YJ", "onResponce 성공: " + result?.toString());
                }
                else{
                    Log.d("YJ", "onResponce 실패")
                }
            }
            override fun onFailure(call: Call<GpsModel>, t: Throwable) {
                Log.d("YJ", "네트워크 에러" + t.message.toString())
            }
        })

        // 현재 위치 마커
        marker.position = LatLng(37.6281, 127.0905)
        marker.map = naverMap
        marker.icon = MarkerIcons.BLACK
        marker.iconTintColor = Color.RED // 현재위치 마커 빨간색으로

        // 장소 리스트 마커
        marker1.position = LatLng(37.62444907132257, 127.09321109051345)
        marker1.map = naverMap
        marker1.captionText = "화랑대 철도공원"

        marker2.position = LatLng(37.608990485010956, 127.0703518565252)
        marker2.map = naverMap // 고씨네
        marker2.captionText = "중랑천 산책로"
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
    companion object {
        lateinit var naverMap: NaverMap
    }
}