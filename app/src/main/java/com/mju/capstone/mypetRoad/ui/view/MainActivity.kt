package com.mju.capstone.mypetRoad.ui.view

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mju.capstone.mypetRoad.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체
    private lateinit var naverMap: NaverMap
    private val marker = Marker()
    private val marker1 = Marker()
    private val marker2 = Marker()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mju.capstone.mypetRoad.R.layout.activity_main)

        mapView = findViewById(com.mju.capstone.mypetRoad.R.id.nav_host_fragment)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(@NonNull naverMap: NaverMap) {
        this.naverMap = naverMap

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
}