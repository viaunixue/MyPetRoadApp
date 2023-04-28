package com.mju.capstone.mypetRoad.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MapInfo")
data class AddressHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val lat: Double,
    val lng: Double
)