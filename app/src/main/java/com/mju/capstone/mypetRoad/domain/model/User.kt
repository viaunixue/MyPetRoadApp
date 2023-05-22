package com.mju.capstone.mypetRoad.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userName") val name: String,
    @SerializedName("userAddress") val address: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("userPhone") val phone: String
)
