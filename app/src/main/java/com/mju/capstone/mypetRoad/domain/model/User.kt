package com.mju.capstone.mypetRoad.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class User (
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String
)