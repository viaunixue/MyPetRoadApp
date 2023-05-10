package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.domain.model.GpsModel
import com.mju.capstone.mypetRoad.domain.model.Login
import com.mju.capstone.mypetRoad.domain.model.Pet
import com.mju.capstone.mypetRoad.domain.model.User
import com.mju.capstone.mypetRoad.data.response.signUp.LoginResponse
import com.mju.capstone.mypetRoad.data.response.signUp.PetResponse
import com.mju.capstone.mypetRoad.data.response.signUp.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface PetRoadApi {
    @GET("/api/gps")
    fun getGps() : Call<GpsModel>

    @POST("/api/login")
    fun login(@Body request: Login): Call<LoginResponse>

    @POST("/api/users")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun postUser(@Body request: User): Call<UserResponse>

    @POST("/api/pets")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun postPet(@Body request: Pet): Call<PetResponse>

//    @GET(Url.LOC_URL)
//    suspend fun getReverseGeoCode(
//        @Header("ID") id: Long = 1,
//        @Query("latitude") latitude: Double,
//        @Query("longitude") longitude: Double,
//        @Query("altitude") altitude: Double,
//        @Query("createTime") createTime: Date
//    ): Response<AddressResponse>
}