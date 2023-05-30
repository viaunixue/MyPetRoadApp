package com.mju.capstone.mypetRoad.data.dto.coords

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mju.capstone.mypetRoad.domain.model.Pet
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class CoordDto(
    @SerializedName("latitude") @Expose val latitude: Double, // 위도
    @SerializedName("longitude") @Expose val longitude: Double, // 경도
    @SerializedName("stayTime") @Expose val stayTime: Long, // 해당 좌표에 머문 시간
    @SerializedName("pet") @Expose val pet: Pet // 해당 좌표를 표시한 펫의 정보
): Parcelable