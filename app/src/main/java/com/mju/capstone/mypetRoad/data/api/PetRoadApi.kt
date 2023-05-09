package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.data.domain.dto.GpsModel
import com.mju.capstone.mypetRoad.data.domain.dto.Pet
import com.mju.capstone.mypetRoad.data.domain.dto.User
import com.mju.capstone.mypetRoad.data.response.naverAddress.AddressResponse
import com.mju.capstone.mypetRoad.data.response.signUp.PetResponse
import com.mju.capstone.mypetRoad.data.response.signUp.UserResponse
import com.mju.capstone.mypetRoad.data.url.Url
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.net.CacheRequest
import java.sql.Date

interface PetRoadApi {
    @GET("/api/gps")
    fun getGps() : Call<GpsModel>

    @POST("/api/login")
    fun login(@Body request: User): Call<User>

    @POST("/api/user")
    fun postUser(@Body request: User): Call<UserResponse>

    @POST("/api/pet")
    fun postPet(@Body request: Pet): Call<PetResponse>

    @GET(Url.LOC_URL)
    suspend fun getReverseGeoCode(
        @Header("ID") id: Long = 1,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("altitude") altitude: Double,
        @Query("altitude") createTime: Date
    ): Response<AddressResponse>
}