package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.data.dto.trackerInfo.TrackerDto
import com.mju.capstone.mypetRoad.domain.model.GpsModel
import retrofit2.Call
import retrofit2.http.*

interface TrackerApi {
    @GET("/api/gps")
    fun getGps() : Call<TrackerDto>

}