package com.mju.capstone.mypetRoad.domain.model

import android.widget.ImageView
import java.sql.Time
import java.util.*

data class WalkingLog (
    val LogImage: Int,
    val date: String,
    val content: String,
    val distance: Double,
    val lab: String,
    val time: String
)