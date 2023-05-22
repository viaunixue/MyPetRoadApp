package com.mju.capstone.mypetRoad.data.dto.walkingInfo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mju.capstone.mypetRoad.data.dto.trackerInfo.TrackerDto
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class WalkingDto(
    @SerializedName("startPoint") @Expose val startPoint: Ping,
    @SerializedName("endPoint") @Expose val endPoint: Ping,
    @SerializedName("roadMap") @Expose val roadMap: RoadMap,
    @SerializedName("activity") @Expose val activity: Activity,
    @SerializedName("walkDate") @Expose val walkDate: Date
): Parcelable