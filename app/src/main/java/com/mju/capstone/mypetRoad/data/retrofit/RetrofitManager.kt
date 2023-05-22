package com.mju.capstone.mypetRoad.data.retrofit

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.domain.model.GpsModel
import androidx.annotation.RequiresApi
import com.mju.capstone.mypetRoad.domain.model.Login
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import com.mju.capstone.mypetRoad.data.dto.signUp.PetDto
import com.mju.capstone.mypetRoad.data.dto.signUp.UserDto
import com.mju.capstone.mypetRoad.data.dto.trackerInfo.TrackerDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.PingRequestDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.WalkingDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.WalkingRequestDto
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.util.Distance
import com.mju.capstone.mypetRoad.util.Route
import com.mju.capstone.mypetRoad.views.MainActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MarkerIcons
import okhttp3.ResponseBody
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
    val pl : MutableList<PingRequestDto> = mutableListOf()
    val key = "ping_list"

    fun postLogin(
        id: String,
        password: String,
        context: Context
    ){
        val loginRequest = Login(id, password)
        val loginCall = serverInstance.login(loginRequest)
        loginCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("user", "Responce : $response")
                Log.d("user", "ResponceBody 성공: " + response.body())
                Log.d("user", "ResponceHeader 성공: " + response.headers())
                val jwt = response.headers().get("Authorization")
                Log.d("user", "tk : $jwt")

                if (response.isSuccessful) {
                    val result : ResponseBody? = response.body()
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

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // handle error
                Log.d("user", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    //사용자 정보 post
    fun postUser(
        name: String,
        address: String,
        userId: String,
        password: String,
        phone: String,
    ){
        val userRequest = User(name, address, userId, password, phone)
        val userCall = serverInstance.postUser(userRequest)

        userCall.enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                if (response.isSuccessful) {
                    val result: UserDto? = response.body()
                    Log.d("user", "onResponce 성공: " + result?.toString());
                } else {
                    Log.d("user", "onResponce 실패" + response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                // handle error
                Log.d("user", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    //애완동물 정보 post
    fun postPet(
        name: String,
        age: Int,
        sex: String,
        weight: Float,
        isNeutered: Boolean?,
        species: String
    ){
        val petRequest = Pet(name, age, sex, weight, isNeutered, species)
//        val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
//        val jwt = sharedPreferences.getString("jwt_token", null)
        val petCall = serverInstance.postPet(petRequest)

        petCall.enqueue(object : Callback<PetDto> {
            override fun onResponse(call: Call<PetDto>, response: Response<PetDto>) {
                if (response.isSuccessful) {
                    val result: PetDto? = response.body()
                    Log.d("pet", "onResponce 성공: " + result?.toString());
                } else {
                    Log.d("pet", "onResponce 실패" + response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<PetDto>, t: Throwable) {
                // handle error
                Log.d("pet", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    //실시간 위치표시
    fun getGPS(
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
                            OverlayImage.fromResource(R.drawable.marker_icon)
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

    //핑 받아서 실시간으로 경로그리기
    fun getPings(
        naverMap: NaverMap,
    ) {
        trackerInstance.getGpsPing().enqueue(object : Callback<PingRequestDto>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<PingRequestDto>, response: Response<PingRequestDto>) {
                //
                if(response.isSuccessful){
                    val result: PingRequestDto? = response.body()
                    if (result != null) {
                        val coord = LatLng(result.latitude, result.longitude)
                        var differenceInSeconds: Long = 0
                        if(Config.isWalking){
                            if(!hashMap.containsKey(key)){
                                pl.add(result)
                                hashMap[key] = pl
                            }else{
                                //마지막 값과 다른 값일 경우
                                if(!hashMap[key]?.last()?.equals(result)!!){
                                    hashMap[key]?.let { Distance.addDistance(it, coord) }

                                    val dateString1 = hashMap[key]?.last()?.createTime
                                    val dateString2 = result.createTime

                                    val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                                    val localDateTime1: LocalDateTime = LocalDateTime.parse(dateString1, dateTimeFormat)
                                    val localDateTime2: LocalDateTime = LocalDateTime.parse(dateString2, dateTimeFormat)

                                    val duration: Duration = Duration.between(localDateTime1, localDateTime2)
                                    differenceInSeconds = duration.seconds
                                    Log.d("differenceInSeconds", "$differenceInSeconds")
                                    pl.add(result)
                                    hashMap[key] = pl
                                }
                            }
                        }
                        naverMap.let {
                            Log.i("ping", hashMap[key].toString())

                            Route.addPing(coord, differenceInSeconds)
                            Route.setMap(naverMap)

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

    //list받아서 경로그리기
    fun drawRoadMap(
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
                                Route.addPing(coord, 0)
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

    //산책끝나면 정보전송
    fun WalkingOver(
        durationTime: Long,
        roadMapName: String,
        travelDistance: Double,
        burnedCalories: Int,
        currentDate: String
    ){
        val p : MutableList<PingRequestDto> = hashMap[key] as MutableList<PingRequestDto>
        val walkingRequestDto = WalkingRequestDto(roadMapName, durationTime, travelDistance, burnedCalories, p, currentDate)

        Log.i("WalkOver", "$walkingRequestDto")
        serverInstance.postWalk(Config.petId, walkingRequestDto).enqueue(object : Callback<WalkingDto>{
            override fun onResponse(call: Call<WalkingDto>, response: Response<WalkingDto>) {
                if(response.isSuccessful){
                    var result: WalkingDto? = response.body()
                    Log.d("WalkOver", "onResponce 성공: " + result?.toString());
                }
                else{
                    Log.d("WalkOver", "onResponce 실패" + response.errorBody()?.string())
                }
            }
            override fun onFailure(call: Call<WalkingDto>, t: Throwable) {
                Log.d("WalkOver", "네트워크 에러 : " + t.message.toString())
            }
        })
    }
}