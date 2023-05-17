package com.mju.capstone.mypetRoad.data.dto.walkingInfo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mju.capstone.mypetRoad.data.dto.trackerInfo.TrackerDto
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class WalkingRequestDto(
    @SerializedName("roadMapName") @Expose val roadMapName: String,
    @SerializedName("walkedTime") @Expose val walkedTime: Long,
    @SerializedName("travelDistance") @Expose val travelDistance: Double,
    @SerializedName("burnedCalories") @Expose val burnedCalories: Int,
    @SerializedName("pingList") @Expose val pingList: List<TrackerDto>,
    @SerializedName("walkDate") @Expose val walkDate: Date
): Parcelable