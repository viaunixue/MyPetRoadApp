package com.mju.capstone.mypetRoad.util

import android.graphics.Color
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.MultipartPathOverlay
import com.naver.maps.map.overlay.PathOverlay
import kotlin.properties.Delegates

object Route {
    val path = MultipartPathOverlay()
    val coordParts = mutableListOf<MutableList<LatLng>>(
//        mutableListOf<LatLng>(),//가장 옅은 색
    )
    val colorParts = mutableListOf<MultipartPathOverlay.ColorPart>(
//        MultipartPathOverlay.ColorPart(
//            Color.RED, Color.WHITE, Color.GRAY, Color.LTGRAY),
    )
    val coord = mutableListOf<LatLng>()
    fun addPing(latLng: LatLng, differenceInSeconds: Long){

        if(differenceInSeconds < 11) {
//            colorParts.add()
        } else if(differenceInSeconds in 11..19) {

        } else {

        }
        //첫 add
        if(coordParts[1].size < 2) {
            coordParts[1].add(latLng)
        }
        coordParts[1].add(latLng)

        path.coordParts = coordParts
        path.colorParts = colorParts
    }
    fun clearPing(){
        path.coordParts.clear()
    }
    fun setMap(map: NaverMap){
        path.map = map
    }
}