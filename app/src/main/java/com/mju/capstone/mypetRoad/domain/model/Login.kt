package com.mju.capstone.mypetRoad.domain.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String
)