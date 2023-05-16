package com.mju.capstone.mypetRoad.data.dto.walkingInfo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class Ping(
    @SerializedName("id") @Expose val id: Long?,
    @SerializedName("latitude") @Expose val latitude: Double,
    @SerializedName("longitude") @Expose val longitude: Double,
    @SerializedName("altitude") @Expose val altitude: Double,
    @SerializedName("timeStamp") @Expose val activityId: Date?
): Parcelable