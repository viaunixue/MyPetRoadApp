package com.mju.capstone.mypetRoad.data.repository

import com.mju.capstone.mypetRoad.data.entity.LocationEntity
import com.mju.capstone.mypetRoad.data.network.MapApiService
import com.mju.capstone.mypetRoad.di.annotation.dispatcherModule.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddressHistoryRepository @Inject constructor(
    private val mapApiService: MapApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AddressHistoryRepositoryInterface {
    override suspend fun getReverseGeoInformation(locationLatLngEntity: LocationEntity) =
        withContext(ioDispatcher) {
            val response = mapApiService.getReverseGeoCode(
                latitude = locationLatLngEntity.latitude,
                longtitue = locationLatLngEntity.longitude,
                altitude = locationLatLngEntity.altitude,
                creatTime = locationLatLngEntity.creatTime,
            )
            if (response.isSuccessful) {
                response.body()?.addressInfo
            } else {
                null
            }
        }
}