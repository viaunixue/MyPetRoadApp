package com.mju.capstone.mypetRoad.domain.model

data class WalkingLog(
    var LogImage: Int,
    var date: String,
    var distance: Float,
    var calories: Int,
    var time: String
)