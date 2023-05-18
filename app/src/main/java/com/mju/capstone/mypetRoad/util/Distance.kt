package com.mju.capstone.mypetRoad.util

import android.util.Log
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.PingRequestDto
import com.naver.maps.geometry.LatLng

object Distance {
    //거리는 미터 단위
    var totalDistance : Double = 0.0
    fun addDistance(pingList: List<PingRequestDto>,latLng2: LatLng){
        val latLng1 = LatLng(pingList.last().latitude, pingList.last().longitude)
        totalDistance += latLng1.distanceTo(latLng2)
        Log.i("Distance","$totalDistance")
    }
    fun clearDistance(){
        totalDistance = 0.0
    }
}