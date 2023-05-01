package com.mju.capstone.mypetRoad.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class LocationEntity (
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var altitude: Double = 0.0,
    var createTime: Date
) : Parcelable