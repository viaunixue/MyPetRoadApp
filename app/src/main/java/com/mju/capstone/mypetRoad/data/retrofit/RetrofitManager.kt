package com.mju.capstone.mypetRoad.data.retrofit

import android.util.Log
import com.mju.capstone.mypetRoad.domain.model.GpsModel
import com.mju.capstone.mypetRoad.domain.model.Login
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import com.mju.capstone.mypetRoad.data.dto.signUp.LoginDto
import com.mju.capstone.mypetRoad.data.dto.signUp.PetDto
import com.mju.capstone.mypetRoad.data.dto.signUp.UserDto
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager {
    companion object{
        val instance = RetrofitManager()
    }

    private val serverInstance = RetrofitInstance.serverService
    private val trackerInstance = RetrofitInstance.trackerService

    fun postLogin(
        id: String,
        password: String,
    ){
        val loginRequest = Login(id, password)
        val loginCall = serverInstance.login(loginRequest)
        loginCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("user", "Responce : $response");
                Log.d("user", "ResponceBody 성공: " + response.body());
                Log.d("user", "ResponceHeader 성공: " + response.headers());

                if (response.isSuccessful) {
                    val result = response.body()
                    Log.d("user", "onResponce 성공: " + result?.toString());

                } else {
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
        phone: String
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

    fun getGPS(
        naverMap: NaverMap,
        marker: Marker
    ) {
        trackerInstance.getGps().enqueue(object : Callback<GpsModel>{
            override fun onResponse(call: Call<GpsModel>, response: Response<GpsModel>) {
                if(response.isSuccessful){
                    var result: GpsModel? = response.body()
                    if (result != null) {
                        marker.position = LatLng(result.latitude, result.longitude,)
                        marker.map = naverMap // 고씨네
                        marker.captionText = "GPS 위치마커"
                    }
                    Log.d("YJ", "onResponce 성공: " + result?.toString());
                }
                else{
                    Log.d("YJ", "onResponce 실패" + response.errorBody()?.string())
                }
            }
            override fun onFailure(call: Call<GpsModel>, t: Throwable) {
                Log.d("YJ", "네트워크 에러 : " + t.message.toString())
            }
        })
    }
}