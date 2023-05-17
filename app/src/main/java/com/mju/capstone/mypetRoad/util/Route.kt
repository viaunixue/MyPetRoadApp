package com.mju.capstone.mypetRoad.util

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.PathOverlay

object Route {
    val path = PathOverlay()
    val coords = mutableListOf<LatLng>()
    fun addPing(latLng: LatLng){
        //첫 add
        if(coords.size < 2) {
            coords.add(latLng)
        }
        coords.add(latLng)
        path.coords = coords
    }
    fun clearPing(){
        path.coords.clear()
    }
    fun setMap(map: NaverMap){
        path.map = map
    }
}