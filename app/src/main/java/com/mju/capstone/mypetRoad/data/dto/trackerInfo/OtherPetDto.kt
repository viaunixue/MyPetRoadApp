package com.mju.capstone.mypetRoad.data.dto.trackerInfo

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class OtherPetDto(
    @SerializedName("id") @Expose val id: Long?,
    @SerializedName("name") @Expose val petName: String,
    @SerializedName("age") @Expose val petAge: Int,
    @SerializedName("sex") @Expose val petSex: String,
    @SerializedName("weight") @Expose val petWeight: Float,
    @SerializedName("isNeutered") @Expose val petIsNeutered: Boolean,
    @SerializedName("species") @Expose val petSpecies: String
): Parcelable