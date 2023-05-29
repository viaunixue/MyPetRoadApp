package com.mju.capstone.mypetRoad.domain.model

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.parcelize.Parcelize
import java.sql.Time
import java.util.*

@Parcelize
data class WalkingLog (
    var LogImage: Int,
    var date: String,
    var distance: Float,
    var calories: Int,
    var time: String
): Parcelable