package com.mju.capstone.mypetRoad.data.repository

import com.mju.capstone.mypetRoad.data.entity.LocationEntity
import com.mju.capstone.mypetRoad.data.network.TrackerApiService
import com.mju.capstone.mypetRoad.di.annotation.dispatcherModule.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddressHistoryRepository @Inject constructor(
    private val trackerApiService: TrackerApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AddressHistoryRepositoryInterface {
    override suspend fun getReverseGeoInformation(locationLatLngEntity: LocationEntity) =
        withContext(ioDispatcher) {
            val response = trackerApiService.getReverseGeoCode(
                latitude = locationLatLngEntity.latitude,
                longitude = locationLatLngEntity.longitude,
                altitude = locationLatLngEntity.altitude,
                createTime = locationLatLngEntity.createTime,
            )
            if (response.isSuccessful) {
                response.body()?.addressInfo
            } else {
                null
            }
        }
}