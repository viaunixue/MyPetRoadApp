package com.mju.capstone.mypetRoad.data.dto.trackerInfo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class TrackerDto(
    @SerializedName("id") @Expose val id: Long?,
    @SerializedName("latitude") @Expose val latitude: Double,
    @SerializedName("longitude") @Expose val longitude: Double,
    @SerializedName("altitude") @Expose val altitude: Double,
    @SerializedName("createTime") @Expose val createTime: Date?
): Parcelable