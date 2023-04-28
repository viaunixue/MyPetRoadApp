package com.mju.capstone.mypetRoad.data.repository

import com.mju.capstone.mypetRoad.data.entity.LocationEntity
import com.mju.capstone.mypetRoad.data.response.naverAddress.AddressInfo

interface AddressHistoryRepositoryInterface {
    suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationEntity
    ): AddressInfo?
}