package com.mju.capstone.mypetRoad.util

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.PathOverlay

object Route {
    val path = PathOverlay()
    fun addPing(latitude: Double, longitude: Double){
        path.coords.add(LatLng(latitude, longitude))
    }
    fun clearPing(){
        val coords = mutableListOf<LatLng>()
        path.coords = coords
    }
    fun setMap(map: NaverMap){
        path.map = map
    }
}