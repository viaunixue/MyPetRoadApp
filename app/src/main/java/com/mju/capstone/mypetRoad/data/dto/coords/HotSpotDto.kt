package com.mju.capstone.mypetRoad.data.dto.coords

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class HotSpotDto(
    @SerializedName("number") @Expose val id: Int?, // 좌표 평면상 스팟의 위치(인덱스 번호)
    @SerializedName("isHot") @Expose val isHot: Boolean, // 해당 스팟이 핫스팟인지 여부
    @SerializedName("width") @Expose val width: Double, // 한 스팟의 가로 길이
    @SerializedName("height") @Expose val height: Double, // 한 스팟의 세로 길이
    @SerializedName("latitude") @Expose val latitude: Double, // 스팟의 중심 latitude 좌표
    @SerializedName("longitude") @Expose val longitude: Double, // 스팟의 중심 longitude 좌표
    @SerializedName("minLatRange") @Expose val minLatRange: Double, // 해당 스팟의 latitude 최소 좌표 범위 값
    @SerializedName("maxLatRange") @Expose val maxLatRange: Double, // 해당 스팟의 latitude 최대 좌표 범위 값
    @SerializedName("minLngRange") @Expose val minLngRange: Double, // 해당 스팟의 longitude 최소 좌표 범위 값
    @SerializedName("maxLngRange") @Expose val maxLngRange: Double, // 해당 스팟의 longitude 최대 좌표 범위 값
    @SerializedName("coordList") @Expose val coordList: List<CoordDto> // 해당 스팟에 있는 좌표열 리스트
): Parcelable