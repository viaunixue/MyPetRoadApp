package com.mju.capstone.mypetRoad.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

object Config {
    var isWalking : Boolean = false

    //로그인 시 사용자 정보랑 펫 정보가 여기 저장됨
    lateinit var user : User
    lateinit var pet : Pet

    //오늘 날짜와 매개변수의 날짜가 같은지 함수로 이 변수를 초기화
    var todayIsWalked : Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    fun isDateToday(lastestDate: Date): Boolean {
//        val today = LocalDate.now(ZoneId.systemDefault())
//        val compareDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        val today: LocalDate = LocalDate.now() // 현재 날짜 가져오기 (로컬 위치 기준)
        val date: Date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant())// Date타입으로 변환하기 위한 폼
        val dateFormat = SimpleDateFormat("yyyy-MM-dd") // 시간 포멧
        val formattedDate: String = dateFormat.format(date) // 폼에 포멧 적용
        val formattedDate2: String = dateFormat.format(lastestDate) // 폼에 포멧 적용

        return formattedDate == formattedDate2
    }
}