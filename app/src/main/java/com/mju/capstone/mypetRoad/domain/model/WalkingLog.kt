package com.mju.capstone.mypetRoad.domain.model

import android.widget.ImageView
import java.sql.Time
import java.util.*

data class WalkingLog (
    var LogImage: Int,
    var date: String,
    var distance: Float,
    var calories: Int,
    var time: String
)