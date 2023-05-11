package com.mju.capstone.mypetRoad.data.retrofit

import com.mju.capstone.mypetRoad.data.api.ServerApi
import com.mju.capstone.mypetRoad.data.api.TrackerApi
import com.mju.capstone.mypetRoad.util.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.security.Provider.Service

object RetrofitInstance {
    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Url.LOC_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
            .build()
    }

    val serverService: ServerApi = retrofit.create(ServerApi::class.java)
    val trackerService: TrackerApi = retrofit.create(TrackerApi::class.java)
}