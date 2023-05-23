package com.mju.capstone.mypetRoad.util

import android.graphics.Color
import android.util.Log
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.MultipartPathOverlay
import com.naver.maps.map.overlay.PathOverlay
import retrofit2.http.Multipart
import kotlin.properties.Delegates

object Route {

    val path = MultipartPathOverlay()

    fun addPing(latLngList: MutableList<LatLng>, sl: List<Long>) {
//        if(latLngList.size < 3) {
//            val latlng = listOf(latLngList[latLngList.size-2], latLngList.last())
//            cdList.add(latlng)
//        }
        val cdList = mutableListOf<List<LatLng>>() //coordParts에 대입할 리스트
        val cdColorList = mutableListOf<MultipartPathOverlay.ColorPart>() //coordColorParts에 대입할 색상 리스트

        for((index, value) in latLngList.withIndex()){
            if(index == latLngList.size-1)
                break

            // 선 리스트를 만듬
            val latlng = listOf(value, latLngList[index+1])
            cdList.add(latlng)


            //선의 시간 가중치 값에 따라 색상 부여
            //가중치 기준값은 향후 변경 필요
            if(sl[index] < 11) {
                cdColorList.add(
                    MultipartPathOverlay.ColorPart(
                        Color.RED, Color.RED, Color.RED, Color.RED),
                )
            }else if(sl[index] < 21) {
                cdColorList.add(
                    MultipartPathOverlay.ColorPart(
                        Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE),
                )
            }else {
                cdColorList.add(
                    MultipartPathOverlay.ColorPart(
                        Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN),
                )
            }
        }

        Log.d("cdList", "$cdList")
        Log.d("cdColorList", "$cdColorList")

        // 각 리스트를 대입
        path.coordParts = cdList
        path.colorParts = cdColorList
    }


    fun clearPing(){
//        path.coordParts.clear() // 영준 ver
        path.coordParts.clear() // 승수 ver
        path.colorParts.clear()
    }
    fun setMap(map: NaverMap){
        path.map = map
    }
}

