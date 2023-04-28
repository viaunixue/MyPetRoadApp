package com.mju.capstone.mypetRoad.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationEntity (
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
) : Parcelable