package com.mju.capstone.mypetRoad.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import com.naver.maps.map.overlay.Marker
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object Config {
    var isWalking : Boolean = false

    var gpsMarker: Marker? = null

    // 로그인 시 사용자 정보랑 펫 정보가 여기 저장됨
    lateinit var user : User
    lateinit var pet : Pet

    // 오늘 날짜와 매개변수의 날짜가 같은지 함수로 이 변수를 초기화
    var todayIsWalked : Boolean = false
    var todayIsWalkedString : ObservableField<String> = ObservableField("오늘 산책 안함")

    @RequiresApi(Build.VERSION_CODES.O)
    fun isDateToday(lastestDate: Date): Boolean {
        // get요청으로 받아온 최근 산책 날짜 데이터 가공
        val dateString = lastestDate.toString()
        val dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        val localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter)
        val formattedDateString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))

        // 오늘 날짜 데이터 가공
        val today = LocalDateTime.now();
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val formattedDate = formatter.format(today)

        // 두 날짜를 String으로 변경후 년,월,일 까지만 가져와서 비교
        val cuttingDate = formattedDateString.toString().split("T")[0]
        val cuttingToday = formattedDate.toString().split("T")[0]

        return cuttingToday.equals(cuttingDate)
    }

    // 시간 변수
    var startTime : Long = 0
    var endTime : Long = 0
    var pauseTime : Long = 0
    var durationTime : Long = 0
}