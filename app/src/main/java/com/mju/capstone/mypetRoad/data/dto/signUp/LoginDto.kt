package com.mju.capstone.mypetRoad.data.dto.signUp

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginDto(
    @SerializedName("token") @Expose val token: String,
    @SerializedName("user_id") @Expose val userId: String
): Parcelable