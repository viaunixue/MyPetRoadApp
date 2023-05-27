package com.mju.capstone.mypetRoad.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import com.naver.maps.map.overlay.Marker
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

object Config {
    var isWalking : Boolean = false

    var gpsMarker: Marker? = null

    // 로그인 시 사용자 정보랑 펫 정보가 여기 저장됨
    lateinit var user : User
    lateinit var pet : Pet

    // 오늘 날짜와 매개변수의 날짜가 같은지 함수로 이 변수를 초기화
    var todayIsWalked : Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    fun isDateToday(date: Date): Boolean {
        val today = LocalDate.now(ZoneId.systemDefault())
        val compareDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        return compareDate.isEqual(today)
    }

    // 시간 변수
    var startTime : Long = 0
    var endTime : Long = 0
    var pauseTime : Long = 0
    var durationTime : Long = 0
}