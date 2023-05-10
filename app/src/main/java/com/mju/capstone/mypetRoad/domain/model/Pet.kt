package com.mju.capstone.mypetRoad.domain.model

import com.google.gson.annotations.SerializedName

data class Pet(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("sex") val sex: String,
    @SerializedName("weight") val weight: Float,
    @SerializedName("isNeutered") val isNeutered: Boolean?,
    @SerializedName("species") val species: String
)