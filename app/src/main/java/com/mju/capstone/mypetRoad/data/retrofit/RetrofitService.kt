package com.mju.capstone.mypetRoad.data.retrofit

import com.mju.capstone.mypetRoad.domain.model.GpsModel
import retrofit2.http.GET
import retrofit2.Call

interface RetrofitService {
    @GET("/api/gps")
    fun getGps() : Call<GpsModel>
}