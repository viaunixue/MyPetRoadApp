package com.mju.capstone.mypetRoad.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class SignUp (
    @SerializedName("userName") val name: String,
    @SerializedName("userAddress") val address: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("userPassword") val password: String,
    @SerializedName("userPhone") val phone: String,
    @SerializedName("petName") val petName: String,
    @SerializedName("petAge") val age: Int,
    @SerializedName("petSex") val sex: String,
    @SerializedName("petWeight") val weight: Float,
    @SerializedName("petIsNeutered") val isNeutered: Boolean?,
    @SerializedName("petSpecies") val species: String
)