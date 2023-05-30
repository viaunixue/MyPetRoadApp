package com.mju.capstone.mypetRoad.util

import android.util.Log
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.PingRequestDto
import com.naver.maps.geometry.LatLng

object Calories {
    var totalCalories : Int = 0
    var totalSeconds: Long = 0L

    //칼로리 = 체중(kg) x 이동 거리(km) x 이동 시간(시간) x 0.05
    fun updateCalories(differenceInSeconds: Long){
        totalSeconds += differenceInSeconds
        val weight = Config.pet.weight / 1.0
        val distance = Distance.totalDistance / 1000.0
        val hours = totalSeconds / 3600.0
        totalCalories = (weight * distance * hours * 0.05).toInt()
    }

    fun clearCalories(){
        totalCalories = 0
    }
}