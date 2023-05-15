package com.mju.capstone.mypetRoad.di.annotation.networkModule

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