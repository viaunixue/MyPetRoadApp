package com.mju.capstone.mypetRoad.views.feature.walking

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mju.capstone.mypetRoad.util.Calories
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.util.Config.endDate
import com.mju.capstone.mypetRoad.util.DateFormatter
import com.mju.capstone.mypetRoad.util.Distance
import com.mju.capstone.mypetRoad.util.Config.startDate
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.round

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class WalkingViewModel @Inject constructor(

): ViewModel(){
    val petName = ObservableField<String>()

    //텍스트 업데이트 변수
    var distance = ObservableField<String>()
    var secTime = ObservableField<String>()
    var minTime = ObservableField<String>()
    var calories = ObservableField<String>()

    var walkedDate = ObservableField<String>()
    var endTime = ObservableField<String>()
    var startTime = ObservableField<String>()

    fun petInfoUpdateText() {
        val petNameValue = "내새꾸 ${Config.pet.name}"
        petName.set(petNameValue)
    }

    fun updateWalkingText() { //live log 업뎃
        // 텍스트 업데이트 로직
        val durationTime = (System.currentTimeMillis() - Config.startTime) / 1000
        val timeStr = String.format("%d", durationTime/60) + ":" + String.format("%d", durationTime%60)

        distance.set(String.format("%.2f", Distance.totalDistance/1000))
        secTime.set(timeStr)
    }

    fun stopWalkingText() { //live log 업뎃 중지
        // 텍스트 업데이트 로직
        val durationTime = (Config.pauseTime - Config.startTime) / 1000
        val timeStr = String.format("%d", durationTime/60) + ":" + String.format("%d", durationTime%60)

        distance.set(String.format("%.2f", Distance.totalDistance/1000))
        secTime.set(timeStr)
    }

    fun updateEndWalkingText() { // slide 업뎃
        // 텍스트 업데이트 로직
        val durationTime = (System.currentTimeMillis() - Config.startTime) / 1000
        val timeStr = String.format("%d", durationTime/60)

        minTime.set(timeStr)
        calories.set(Calories.totalCalories.toString())
    }

    fun updateDetailWalkingText() { // 결과 화면 업뎃
        // 텍스트 업데이트 로직
        val dateStr = String.format("%tY. %tm. %td (%ta)", startDate, startDate, startDate, startDate)
        val startTimeStr = String.format("%tH시 %tM분", startDate, startDate)
        val endTimeStr = String.format("%tH시 %tM분", endDate, endDate)
        val durationTime = (Config.endTime - Config.startTime) / 1000
        val timeStr = String.format("%d", durationTime/60)

        Log.d("시작시간", startDate.toString())
        Log.d("종료시간", endDate.toString())
        walkedDate.set(dateStr)
        endTime.set(endTimeStr)
        startTime.set(startTimeStr)
        distance.set(String.format("%.2f", Distance.totalDistance/1000))
        minTime.set(timeStr)
        calories.set(Calories.totalCalories.toString())
    }
}