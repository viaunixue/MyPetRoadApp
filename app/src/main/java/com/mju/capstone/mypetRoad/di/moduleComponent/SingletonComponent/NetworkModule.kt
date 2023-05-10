package com.mju.capstone.mypetRoad.di.moduleComponent.SingletonComponent

import com.mju.capstone.mypetRoad.BuildConfig
import com.mju.capstone.mypetRoad.util.Url
import com.mju.capstone.mypetRoad.di.annotation.networkModule.TrackerRetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()

        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @TrackerRetrofitInstance
    @Provides
    @Singleton
    fun provideKakaoRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Url.LOC_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideKakaoController(@TrackerRetrofitInstance retrofit: Retrofit): TrackerApiService {
//        return retrofit.create(TrackerApiService::class.java)
//    }

//    @Provides
//    @Singleton
//    fun provideFirebaseDatabase(): FirebaseDatabase = Firebase.database
}