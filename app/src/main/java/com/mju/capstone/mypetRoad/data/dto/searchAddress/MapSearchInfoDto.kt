package com.mju.capstone.mypetRoad.data.dto.searchAddress

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MapSearchInfoDto (
    val fullAddress: String,
    val name: String
//    val locationLatLng: LocationDto
): Parcelable