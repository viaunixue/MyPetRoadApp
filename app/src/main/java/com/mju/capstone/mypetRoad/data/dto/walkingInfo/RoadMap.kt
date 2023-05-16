package com.mju.capstone.mypetRoad.data.dto.walkingInfo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class RoadMap(
    @SerializedName("id") @Expose val id: Long?,
    @SerializedName("roadMapName") @Expose val roadMapName: String,
    @SerializedName("pingList") @Expose val pingList: List<Ping>
): Parcelable