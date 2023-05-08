package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.data.domain.dto.GpsModel
import com.mju.capstone.mypetRoad.data.response.naverAddress.AddressResponse
import com.mju.capstone.mypetRoad.data.url.Url
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.sql.Date

interface PetRoadApi {
    @GET("/api/gps")
    fun getGps() : Call<GpsModel>

    @POST("/api/login")

    @GET(Url.LOC_URL)
    suspend fun getReverseGeoCode(
        @Header("ID") id: Long = 1,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("altitude") altitude: Double,
        @Query("altitude") createTime: Date
    ): Response<AddressResponse>
}