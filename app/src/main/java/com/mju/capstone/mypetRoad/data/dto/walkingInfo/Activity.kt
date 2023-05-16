package com.mju.capstone.mypetRoad.data.dto.walkingInfo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class Activity(
    @SerializedName("id") @Expose val id: Long?,
    @SerializedName("walkedTime") @Expose val walkedTime: String,
    @SerializedName("burnedCalories") @Expose val burnedCalories: Int,
    @SerializedName("travelDistance") @Expose val travelDistance: Float
): Parcelable