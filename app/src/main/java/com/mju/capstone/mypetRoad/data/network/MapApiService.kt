package com.mju.capstone.mypetRoad.data.network

import com.mju.capstone.mypetRoad.data.response.naverAddress.AddressResponse
import com.mju.capstone.mypetRoad.data.url.Url
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.sql.Date

interface MapApiService {
    @GET(Url.LOC_URL)
    suspend fun getReverseGeoCode(
        @Header("ID") id: Long = 1,
        @Query("latitude") latitude: Double,
        @Query("longtitue") longtitue: Double,
        @Query("altitude") altitude: Double,
        @Query("altitude") creatTime: Date
    ): Response<AddressResponse>
}