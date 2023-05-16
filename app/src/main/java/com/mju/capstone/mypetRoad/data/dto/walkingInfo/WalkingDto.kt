package com.mju.capstone.mypetRoad.data.dto.walkingInfo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mju.capstone.mypetRoad.data.dto.trackerInfo.TrackerDto
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class WalkingDto(
    @SerializedName("id") @Expose val id: Long?,
    @SerializedName("startPoint") @Expose val startPoint: Ping,
    @SerializedName("endPoint") @Expose val endPoint: Ping,
    @SerializedName("roadMapId") @Expose val roadMapId: Double,
    @SerializedName("activityId") @Expose val activityId: Date?,
    @SerializedName("walkDate") @Expose val walkDate: Date
): Parcelable