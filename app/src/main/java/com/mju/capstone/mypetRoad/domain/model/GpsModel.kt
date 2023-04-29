package com.mju.capstone.mypetRoad.domain.model

import java.sql.Date

class GpsModel {
    private var id: Long = 0
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var altitude: Double = 0.0
    private lateinit var createTime: Date

    override fun toString(): String {
        return "GetResult(" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", createTime=" + createTime +
                ")"
    }
}