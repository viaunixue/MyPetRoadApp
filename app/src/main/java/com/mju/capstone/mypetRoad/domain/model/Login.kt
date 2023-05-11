package com.mju.capstone.mypetRoad.domain.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("userId") val userId: String,
    @SerializedName("password") val password: String
)