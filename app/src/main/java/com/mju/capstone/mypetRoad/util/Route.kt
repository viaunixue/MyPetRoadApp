package com.mju.capstone.mypetRoad.util

import android.graphics.Color
import android.util.Log
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.MultipartPathOverlay
import com.naver.maps.map.overlay.PathOverlay
import kotlin.properties.Delegates

object Route {
    val path = MultipartPathOverlay()
    val coordParts = mutableListOf<MutableList<LatLng>>()
    val colorParts = mutableListOf(
        MultipartPathOverlay.ColorPart(Color.RED, Color.BLACK, Color.GRAY, Color.LTGRAY)
    )
    //임시 coordParts 원소 (2개가 채워지면 add된 뒤 clear됨)
    val coord = mutableListOf<LatLng>()
    fun addPing(latLng: LatLng, differenceInSeconds: Long){
        //첫 add
        if(coordParts.size < 1) {
            coordParts.add(mutableListOf(latLng, latLng))
        } else { //첫아닌 add
            coord.add(latLng)
            coordParts.add(coord)
            if(differenceInSeconds < 11) {
                colorParts.add(
                    MultipartPathOverlay.ColorPart(Color.RED, Color.BLACK, Color.GRAY, Color.LTGRAY))
            } else if(differenceInSeconds < 20) {
                colorParts.add(
                    MultipartPathOverlay.ColorPart(Color.BLUE, Color.BLACK, Color.GRAY, Color.LTGRAY))
            } else {
                colorParts.add(
                    MultipartPathOverlay.ColorPart(Color.GREEN, Color.BLACK, Color.GRAY, Color.LTGRAY))
            }
        }
        Log.d("coord", "$coord")
        path.coordParts = coordParts
        path.colorParts = colorParts
        coord.clear()
        coord.add(latLng)
    }
    fun clearPing(){
        path.coordParts.clear()
    }
    fun setMap(map: NaverMap){
        path.map = map
    }
}