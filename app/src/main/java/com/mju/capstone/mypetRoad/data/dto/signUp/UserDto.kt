package com.mju.capstone.mypetRoad.data.dto.signUp

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto (
    @SerializedName("id") @Expose val id: Long
): Parcelable

