package com.mju.capstone.mypetRoad.di.annotation.networkModule

import com.mju.capstone.mypetRoad.data.api.NaverMapApi
import com.mju.capstone.mypetRoad.data.api.TrackerApi
import com.mju.capstone.mypetRoad.util.Url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object naverMapRetrofitInstance {
//
//    // Retrofit
//    @Singleton
//    @Provides
//    fun provideOkhttpClient(): OkHttpClient {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(MoshiConverterFactory.create())
//            .client(okHttpClient)
//            .baseUrl(Url.LOC_URL)
//            .build()
//
////        return Retrofit.Builder()
////            .addConverterFactory(GsonConverterFactory.create())
////            .baseUrl(Url.LOC_URL)
////            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit): NaverMapApi {
//        return retrofit.create(NaverMapApi::class.java)
//    }
//}