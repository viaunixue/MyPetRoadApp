package com.mju.capstone.mypetRoad.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("sex") val sex: String,
    @SerializedName("weight") val weight: Float,
    @SerializedName("isNeutered") val isNeutered: Boolean?,
    @SerializedName("species") val species: String
) : Parcelable