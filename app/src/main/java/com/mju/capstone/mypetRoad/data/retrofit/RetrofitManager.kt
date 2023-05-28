package com.mju.capstone.mypetRoad.data.retrofit

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.data.dto.signUp.UserResponseDto
import com.mju.capstone.mypetRoad.domain.model.Login
import com.mju.capstone.mypetRoad.domain.model.SignUp
import com.mju.capstone.mypetRoad.data.dto.trackerInfo.TrackerDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.Ping
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.PingRequestDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.WalkingDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.WalkingRequestDto
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import com.mju.capstone.mypetRoad.util.Calories
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.util.Config.gpsMarker
import com.mju.capstone.mypetRoad.util.Distance
import com.mju.capstone.mypetRoad.util.Route
import com.mju.capstone.mypetRoad.views.MainActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MarkerIcons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.HashMap

class RetrofitManager {
    companion object{
        val instance = RetrofitManager()
    }

    private val serverInstance = RetrofitInstance.serverService
    private val trackerInstance = RetrofitInstance.trackerService
    val hashMap : HashMap<String, List<PingRequestDto>> = HashMap()
    val pl : MutableList<PingRequestDto> = mutableListOf() // 핑 리스트
    val cl : MutableList<LatLng> = mutableListOf() // coords 리스트
    val key = "ping_list" // 해쉬 키값
    val sl : MutableList<Long> = mutableListOf() // differenceInSeconds 리스트

    fun postLogin( //Login하면 token을 받고 Config의 pet, user 값을 초기화
        id: String,
        password: String,
        context: Context
    ){
        val loginRequest = Login(id, password)
        val loginCall = serverInstance.login(loginRequest)
        loginCall.enqueue(object : Callback<UserResponseDto> {
            override fun onResponse(call: Call<UserResponseDto>, response: Response<UserResponseDto>) {
                Log.d("user", "Responce : $response")
                Log.d("user", "ResponceBody 성공: " + response.body())
                Log.d("user", "ResponceHeader 성공: " + response.headers())
                val jwt = response.headers().get("Authorization")
                Log.d("user", "tk : $jwt")

                if (response.isSuccessful) {
                    val result : UserResponseDto? = response.body()
                    if (result != null) {
                        Config.user = User (result.name, result.address, result.userId, result.phone)
                        Config.pet = Pet (
                            result.myPet.id, result.myPet.name, result.myPet.age,
                            result.myPet.sex, result.myPet.weight, result.myPet.isNeutered, result.myPet.species)
                    }
                    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

                    val editor = sharedPreferences.edit()
                    editor.putString("jwt_token", jwt)
                    editor.apply()
                    Log.d("user", "onResponce 성공: " + result?.toString())

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "등록되지 않은 아이디 혹은 비밀번호입니다.", Toast.LENGTH_SHORT).show()
                    Log.d("user", "onResponce 실패" + response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<UserResponseDto>, t: Throwable) {
                // handle error
                Log.d("user", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    //사용자 정보 post
    fun postUser( //회원가입 정보를 서버에 Post
        name: String,
        address: String,
        userId: String,
        password: String,
        phone: String,
        petname: String,
        petage: Int,
        petsex: String,
        petweight: Float,
        petisNeutered: Boolean?,
        petspecies: String
    ){
        val signUpRequest = SignUp(
            name, address, userId, password, phone, petname, petage, petsex, petweight, petisNeutered, petspecies
        )
        val userCall = serverInstance.postUser(signUpRequest)

        userCall.enqueue(object : Callback<UserResponseDto> {
            override fun onResponse(call: Call<UserResponseDto>, response: Response<UserResponseDto>) {
                if (response.isSuccessful) {
                    val result: UserResponseDto? = response.body()
                    Log.d("user", "onResponce 성공: " + result?.toString());
                } else {
                    Log.d("user", "onResponce 실패" + response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<UserResponseDto>, t: Throwable) {
                // handle error
                Log.d("user", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    fun getGPS( //naverMap에 실시간 위치표시
        naverMap: NaverMap,
        context: Context
    ) {
        val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        val jwt = sharedPreferences.getString("jwt_token", null)
        trackerInstance.getGps().enqueue(object : Callback<TrackerDto>{
            override fun onResponse(call: Call<TrackerDto>, response: Response<TrackerDto>) {
                if(response.isSuccessful){
                    val result: TrackerDto? = response.body()
                    if (result != null) {
                        naverMap.let {
                            val coord = LatLng(result.latitude, result.longitude)

                            val locationOverlay = it.locationOverlay
                            locationOverlay.isVisible = true
                            locationOverlay.position = coord

                            it.moveCamera(CameraUpdate.scrollTo(coord))

                            // 이전 마커 제거
                            gpsMarker?.map = null

                            val imageMarkerIcon = OverlayImage.fromResource(R.drawable.marker_icon)
                            gpsMarker = Marker()
                            gpsMarker?.icon = imageMarkerIcon
                            gpsMarker?.position = coord
                            gpsMarker?.map = naverMap
                        }
                    }
                    Log.d("GPS", "onResponce 성공: " + result?.toString());
                }
                else{
                    Log.d("GPS", "onResponce 실패" + response.errorBody()?.string())
                }
            }
            override fun onFailure(call: Call<TrackerDto>, t: Throwable) {
                Log.d("GPS", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    fun getPings( //핑 받아서 실시간으로 경로그리기
        naverMap: NaverMap
    ) {
        trackerInstance.getGpsPing().enqueue(object : Callback<PingRequestDto>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<PingRequestDto>, response: Response<PingRequestDto>) {
                //
                if(response.isSuccessful){
                    val result: PingRequestDto? = response.body()
                    if (result != null) {
                        val coord = LatLng(result.latitude, result.longitude)
                        var differenceInSeconds: Long = 0 // 두 핑 사이의 시간차 (초기값 0)
                         // 산책 중일 시
                        if(!hashMap.containsKey(key)){ // 해쉬맵에 해당 키가 없을시 (첫 핑)
                            pl.add(result)
                            cl.add(coord) // coord 리스트에 추가
                            hashMap[key] = pl
                        }else{ // 해쉬맵에 해당 키가 존재 (첫 핑 이후)
                            //마지막 값과 다른 값일 경우 (트래커 위치가 변경시)
                            if(!hashMap[key]?.last()?.equals(result)!!){
                                cl.add(coord) // coord 리스트에 추가
                                hashMap[key]?.let { Distance.addDistance(it, coord) }

                                val dateString1 = hashMap[key]?.last()?.createTime // 기존 핑 시간
                                val dateString2 = result.createTime // 지금 받은 핑 시간

                                val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // 시간 포멧
                                val localDateTime1: LocalDateTime = LocalDateTime.parse(dateString1, dateTimeFormat) // String -> LocalDateTime 변경 + 포멕 적용
                                val localDateTime2: LocalDateTime = LocalDateTime.parse(dateString2, dateTimeFormat)

                                val duration: Duration = Duration.between(localDateTime1, localDateTime2) // 두 시간의 차이 계산
                                differenceInSeconds = duration.seconds // 시간차를 초로 나타냄 (long 타입)
                                Calories.updateCalories(differenceInSeconds) // 소모 칼로리 업데이트
                                Log.d("differenceInSeconds", "$differenceInSeconds")
                                sl.add(differenceInSeconds) //시간 가중치 리스트에 값 추가 (점이 2개 이상일 때부터)
//                                    differenceInSeconds =
//                                        (localDate1 - localDate2) / 1000
                                pl.add(result)
                                hashMap[key] = pl
                            }
                        }
                        naverMap.let {
                            Log.i("ping", hashMap[key].toString())

//                            Route.addPing(coord, differenceInSeconds)
//                            Route.setMap(naverMap)


                            // 점이 3개 이상일때 (즉, 선이 2개 일때부터)
                            if(cl.size > 2) {
                                // coords 리스트와 선의 시간 가중치 리스트도 넘김
                                Route.addPing(cl, sl)
                                Route.setMap(naverMap)
                            }

                            val locationOverlay = it.locationOverlay
                            locationOverlay.isVisible = true
                            locationOverlay.position = coord

                            it.moveCamera(CameraUpdate.scrollTo(coord))
                        }
                    }
                    Log.d("GPS", "onResponce 성공: " + result?.toString());
                }
                else{
                    Log.d("GPS", "onResponce 실패" + response.errorBody()?.string())
                }
            }
            override fun onFailure(call: Call<PingRequestDto>, t: Throwable) {
                Log.d("GPS", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    fun drawRoadMap( //list받아서 경로그리기
        naverMap: NaverMap,
        context: Context
    ) {
        val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        val jwt = sharedPreferences.getString("jwt_token", null)
        trackerInstance.getGpsList().enqueue(object : Callback<List<PingRequestDto>>{
            override fun onResponse(call: Call<List<PingRequestDto>>, response: Response<List<PingRequestDto>>) {
                if(response.isSuccessful){
                    var result: List<PingRequestDto>? = response.body()
                    if (result != null) {
                        for(i in result){
                            naverMap.let {
                                val coord = LatLng(i.latitude, i.longitude)
                                Log.e("ping", "$i")
//                                Route.addPing(coord, 0)
                                Route.setMap(naverMap)
                            }
                        }
                    }
                    Log.d("Ping", "onResponce 성공: " + result?.toString());
                }
                else{
                    Log.d("Ping", "onResponce 실패" + response.errorBody()?.string())
                }
            }
            override fun onFailure(call: Call<List<PingRequestDto>>, t: Throwable) {
                Log.d("Ping", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    fun WalkingOver( //산책이 끝나면 서버에 산책정보를 Post
        durationTime: Long,
        roadMapName: String,
        travelDistance: Double,
        burnedCalories: Int,
        currentDate: String
    ) {
        val pingList = hashMap[key] as? MutableList<PingRequestDto>

        if (pingList != null) {
            val walkingRequestDto = WalkingRequestDto(
                roadMapName,
                durationTime,
                travelDistance,
                burnedCalories,
                pingList,
                currentDate
            )

            Log.i("WalkOver", "$walkingRequestDto")
            serverInstance.postWalk(Config.pet.id, walkingRequestDto).enqueue(object : Callback<WalkingDto> {
                override fun onResponse(call: Call<WalkingDto>, response: Response<WalkingDto>) {
                    if (response.isSuccessful) {
                        var result: WalkingDto? = response.body()
                        Calories.clearCalories() // 칼로리 변수 초기화
                        Log.d("WalkOver", "onResponce 성공: " + result?.toString())
                    } else {
                        Log.d("WalkOver", "onResponce 실패" + response.errorBody()?.string())
                    }
                }

                override fun onFailure(call: Call<WalkingDto>, t: Throwable) {
                    Log.d("WalkOver", "네트워크 에러 : " + t.message.toString())
                }
            })
        } else {
            Log.d("WalkOver", "pingList is null")
        }
    }

    fun getLastestWalk( //마지막 산책로를 naverMap에 그리기
        naverMap: NaverMap,
//        context: Context
    ) {
//        val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
//        val jwt = sharedPreferences.getString("jwt_token", null)
        serverInstance.getLastestWalk(Config.pet.id).enqueue(object : Callback<WalkingDto>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<WalkingDto>, response: Response<WalkingDto>) {
                if(response.isSuccessful){
                    val result: WalkingDto? = response.body()
                    val savedSl: MutableList<Long> = mutableListOf()
                    val savedCl: MutableList<LatLng> = mutableListOf()
                    if (result != null) {
                        // 오늘산책여부
                        Config.todayIsWalked = Config.isDateToday(result.walkDate)


                        for((index, value) in result.roadMap.pingList.withIndex()) {
                            val coord = LatLng(value.latitude, value.longitude)
                            var differenceInSeconds: Long = 0 // 두 핑 사이의 시간차 (초기값 0)
                            savedCl.add(coord)

                            if(index > 0){
                                val dateString1 = result.roadMap.pingList[index - 1].createTime // 이전 핑 시간
                                val dateString2 = value.createTime // 다음 핑 시간

                                val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // 시간 포멧
                                val localDateTime1: LocalDateTime = LocalDateTime.parse(dateString1, dateTimeFormat) // String -> LocalDateTime 변경 + 포멕 적용
                                val localDateTime2: LocalDateTime = LocalDateTime.parse(dateString2, dateTimeFormat)

                                val duration: Duration = Duration.between(localDateTime1, localDateTime2) // 두 시간의 차이 계산
                                differenceInSeconds = duration.seconds // 시간차를 초로 나타냄 (long 타입)
                                Log.d("differenceInSeconds", "$differenceInSeconds")
                                savedSl.add(differenceInSeconds) //시간 가중치 리스트에 값 추가 (점이 2개 이상일 때부터)
                            }
                        }
                        naverMap.let {
                            // coords 리스트와 선의 시간 가중치 리스트도 넘김
                            try {
                                Route.addPing(savedCl, savedSl)
                                Route.setMap(naverMap)
                            } catch (_: java.lang.IllegalArgumentException) {}
                            it.moveCamera(CameraUpdate.scrollTo(savedCl.last()))
                        }
                    }
                    Log.d("LastestWalk", "onResponce 성공: " + result?.toString());
                }
                else{
                    Log.d("LastestWalk", "onResponce 실패" + response.errorBody()?.string())
                }
            }
            override fun onFailure(call: Call<WalkingDto>, t: Throwable) {
                Log.d("LastestWalk", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    fun getAllWalk( //Config.walkList 값을 서버값으로 초기화
//        context: Context
    ) {
//        val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
//        val jwt = sharedPreferences.getString("jwt_token", null)
        serverInstance.getAllWalk(Config.pet.id).enqueue(object : Callback<List<WalkingDto>>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<List<WalkingDto>>, response: Response<List<WalkingDto>>) {
                if(response.isSuccessful){
                    val result: List<WalkingDto>? = response.body()
                    if (result != null) {
                        // 오늘산책여부
                        Config.walkList = result
                    }
                    Log.d("LastestWalk", "onResponce 성공: " + result?.toString());
                }
                else{
                    Log.d("LastestWalk", "onResponce 실패" + response.errorBody()?.string())
                }
            }
            override fun onFailure(call: Call<List<WalkingDto>>, t: Throwable) {
                Log.d("LastestWalk", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

}