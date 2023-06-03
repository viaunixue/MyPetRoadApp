package com.mju.capstone.mypetRoad.util

import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.WalkingDto
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.MultipartPathOverlay
import com.naver.maps.map.overlay.PathOverlay
import retrofit2.http.Multipart
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
            if(sl[index] < 9) {
                cdColorList.add(
                    MultipartPathOverlay.ColorPart(
                        Color.parseColor("#66CC33"),
                        Color.parseColor("#66CC33"),
                        Color.parseColor("#66CC33"),
                        Color.parseColor("#66CC33")),
                )
            }else if(sl[index] < 15) {
                cdColorList.add(
                    MultipartPathOverlay.ColorPart(
                        Color.parseColor("#FFE650"),
                        Color.parseColor("#FFE650"),
                        Color.parseColor("#FFE650"),
                        Color.parseColor("#FFE650")),
                )
            }else {
                cdColorList.add(
                    MultipartPathOverlay.ColorPart(
                        Color.parseColor("#FF0033"),
                        Color.parseColor("#FF0033"),
                        Color.parseColor("#FF0033"),
                        Color.parseColor("#FF0033")
                    ),
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun setWalkLine( //가장 최근 산책로 그려주고 Break.
        naverMap: NaverMap,
        walkYear: String,
        walkMonth: String,
        walkDay: String,
        walkStartTime: Int,
        walkEndTime: Int
    ){
        Log.d("setWalkLine", "Start")
        val savedSl: MutableList<Long> = mutableListOf()
        val savedCl: MutableList<LatLng> = mutableListOf()
        for(result in Config.walkList.reversed()){
            val year = String.format("%tY", result.walkDate)
            val month = String.format("%tm", result.walkDate)
            val day = String.format("%td", result.walkDate)
            val startTimeDate = DateFormatter.decreaseDateBySeconds(result.walkDate, result.activity.walkedTime.toLong())
            val startTime = String.format("%tH%tM", startTimeDate, startTimeDate).toInt()
            val endTime = String.format("%tH%tM", result.walkDate, result.walkDate).toInt()
            if ((year == walkYear && month == walkMonth && day == walkDay) &&
                (startTime >= walkStartTime && endTime <= walkEndTime) && result.roadMap.pingList.size > 1) {
                Log.d("setWalkLine", endTime.toString())
                // 오늘산책여부
                Config.todayIsWalked = Config.isDateToday(result.walkDate)

                for((index, value) in result.roadMap.pingList.withIndex()) {
                    val coord = LatLng(value.latitude, value.longitude)
                    var differenceInSeconds: Long = 0 // 두 핑 사이의 시간차 (초기값 0)
                    savedCl.add(coord)

                    if(index > 0){
                        val dateString1 = result.roadMap.pingList[index-1].createTime // 이전 핑 시간
                        val dateString2 = value.createTime // 다음 핑 시간

                        val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // 시간 포멧
                        val localDateTime1: LocalDateTime = LocalDateTime.parse(dateString1, dateTimeFormat) // String -> LocalDateTime 변경 + 포멕 적용
                        val localDateTime2: LocalDateTime = LocalDateTime.parse(dateString2, dateTimeFormat)

                        val duration: Duration = Duration.between(localDateTime1, localDateTime2) // 두 시간의 차이 계산
                        differenceInSeconds = duration.seconds // 시간차를 초로 나타냄 (long 타입)
                        Log.d("differenceInSeconds", "$differenceInSeconds")
                    }
                    savedSl.add(differenceInSeconds) //시간 가중치 리스트에 값 추가 (점이 2개 이상일 때부터)
                }
                naverMap.let {
                    // coords 리스트와 선의 시간 가중치 리스트도 넘김
                    try {
                        addPing(savedCl, savedSl)
                        setMap(naverMap)
                    } catch (_: java.lang.IllegalArgumentException) {}
                    it.moveCamera(CameraUpdate.scrollTo(savedCl.last()))
                }
                break
            }
        }
        Log.d("setWalkLine", "End")
    }
}

