package com.mju.capstone.mypetRoad.data.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class UserEntity (
    val id: Long?,
    val name: String,
    val address: String,
    val userId: String,
    val password: String,
    val phone: String,
    var createTime: Date
) : Parcelable