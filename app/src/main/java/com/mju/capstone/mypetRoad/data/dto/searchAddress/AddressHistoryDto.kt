package com.mju.capstone.mypetRoad.data.dto.searchAddress

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressHistoryDto (
    val id: Long?,
    val fullAddress: String?,
    val name: String?,
    val lat: Double,
    val lng: Double
) : Parcelable