package com.mju.capstone.mypetRoad.data.response.naverAddress

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Date

data class AddressInfo(
    @SerializedName("ID")
    @Expose val id: Long?,
    @SerializedName("latitude")
    @Expose val latitude: Double?,
    @SerializedName("longtitue")
    @Expose val longtitue: Double?,
    @SerializedName("altitude")
    @Expose val altitude: Double?,
    @SerializedName("creatTime")
    @Expose val creatTime: Date?
)