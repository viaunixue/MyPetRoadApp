package com.mju.capstone.mypetRoad.data.api

import com.mju.capstone.mypetRoad.data.url.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
            .baseUrl(Url.LOC_URL)
            .build()
    }

    val api: PetRoadApi by lazy {
        retrofit.create(PetRoadApi::class.java)
    }

    val service: PetRoadApi = retrofit.create(PetRoadApi::class.java)
}