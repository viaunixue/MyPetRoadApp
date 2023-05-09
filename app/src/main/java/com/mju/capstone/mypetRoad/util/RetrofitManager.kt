package com.mju.capstone.mypetRoad.util

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.mju.capstone.mypetRoad.data.api.RetrofitInstance
import com.mju.capstone.mypetRoad.data.domain.dto.GpsModel
import com.mju.capstone.mypetRoad.data.domain.dto.Pet
import com.mju.capstone.mypetRoad.data.domain.dto.User
import com.mju.capstone.mypetRoad.data.response.signUp.PetResponse
import com.mju.capstone.mypetRoad.data.response.signUp.UserResponse
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager {
    companion object{
        val instance = RetrofitManager()
    }

    private val retrofitInstance = RetrofitInstance.service

    //사용자 정보 post
    fun postUser(
        name: String,
        address: String,
        userId: String,
        password: String,
        phone: String
    ){
        val userRequest = User(name, address, userId, password, phone)
        val userCall = retrofitInstance.postUser(userRequest)

        userCall.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val result: UserResponse? = response.body()
                    Log.d("YJ", "onResponce 성공: " + result?.toString());
                } else {
                    Log.d("YJ", "onResponce 실패")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // handle error
                Log.d("YJ", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    //애완동물 정보 post
    fun postPet(
        name: String,
        age: Int,
        sex: String,
        weight: Float,
        isNeutered: Boolean,
        species: String
    ){
        val petRequest = Pet(name, age, sex, weight, isNeutered, species)
        val petCall = retrofitInstance.postPet(petRequest)

        petCall.enqueue(object : Callback<PetResponse> {
            override fun onResponse(call: Call<PetResponse>, response: Response<PetResponse>) {
                if (response.isSuccessful) {
                    val result: PetResponse? = response.body()
                    Log.d("YJ", "onResponce 성공: " + result?.toString());
                } else {
                    Log.d("YJ", "onResponce 실패")
                }
            }

            override fun onFailure(call: Call<PetResponse>, t: Throwable) {
                // handle error
                Log.d("YJ", "네트워크 에러 : " + t.message.toString())
            }
        })
    }

    fun getGPS(
        naverMap: NaverMap,
        marker: Marker
    ) {
        retrofitInstance.getGps().enqueue(object : Callback<GpsModel>{
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
                    Log.d("YJ", "onResponce 실패")
                }
            }
            override fun onFailure(call: Call<GpsModel>, t: Throwable) {
                Log.d("YJ", "네트워크 에러 : " + t.message.toString())
            }
        })
    }
}