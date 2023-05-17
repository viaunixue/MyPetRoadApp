package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.data.dto.trackerInfo.TrackerDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.Ping
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.PingRequestDto
import com.mju.capstone.mypetRoad.data.dto.walkingInfo.WalkingDto
import retrofit2.Call
import retrofit2.http.*

interface TrackerApi {
    @GET("/api/gps")
    fun getGps(): Call<TrackerDto>

    @GET("/api/gps")
    fun getGpsPing(): Call<PingRequestDto>

//    @GET("/api/pings")
//    fun getPings(@Header("Authorization") jwt_token: String) : Call<WalkingDto>
    @GET("/api/pings")
    fun getPings() : Call<Ping>

    @GET("/api/gps/test")
    fun getGpsList(): Call<List<TrackerDto>>
}