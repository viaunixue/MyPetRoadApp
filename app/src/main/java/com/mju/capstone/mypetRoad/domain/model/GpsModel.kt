package com.mju.capstone.mypetRoad.domain.model

import java.util.Date

class GpsModel {
    var id: Long = 0
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var altitude: Double = 0.0
    lateinit var createTime: Date
    constructor()

    override fun toString(): String {
        return "GetResult(" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", createTime=" + createTime +
                ")"
    }
}