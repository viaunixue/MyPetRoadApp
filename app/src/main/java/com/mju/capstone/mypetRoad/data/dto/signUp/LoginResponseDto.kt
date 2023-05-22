package com.mju.capstone.mypetRoad.data.dto.signUp

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mju.capstone.mypetRoad.domain.model.Pet
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class LoginResponseDto(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("address") @Expose val address: String,
    @SerializedName("userId") @Expose val userId: String,
    @SerializedName("phone") @Expose val phone: String,
    @SerializedName("myPet") @Expose val myPet: Pet,
    @SerializedName("createTime") @Expose val createTime: Date,
): Parcelable