package com.mju.capstone.mypetRoad.data.domain.dto

import com.google.gson.annotations.SerializedName

data class Pet(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("sex") val sex: String,
    @SerializedName("weight") val weight: Float,
    @SerializedName("is_neutered") val isNeutered: Boolean?,
    @SerializedName("species") val species: String
)