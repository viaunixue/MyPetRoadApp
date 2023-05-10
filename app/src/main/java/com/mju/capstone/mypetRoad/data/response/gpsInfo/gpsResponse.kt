package com.mju.capstone.mypetRoad.data.response.gpsInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Date

data class gpsResponse(
    @SerializedName("ID")
    @Expose val id: Long?,
    @SerializedName("latitude")
    @Expose val latitude: Double?,
    @SerializedName("longitude")
    @Expose val longitude: Double?,
    @SerializedName("altitude")
    @Expose val altitude: Double?,
    @SerializedName("createTime")
    @Expose val createTime: Date?
)