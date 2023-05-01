package com.mju.capstone.mypetRoad.presentation.ui.map.navermap

import android.graphics.Color
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MarkerIcons

class MarkerFactory {

    fun createMarker(
        position: LatLng,
        /*category: RestaurantCategory,*/
        tag: Any,
        zIndex: Int = 1,
    ) = Marker().apply {
        this.position = position
        this.tag = tag
        this.zIndex = zIndex
        setMarkerIconAndColor(/*category*/)
    }

    fun createDestMarker() = Marker(
        MarkerIcons.BLACK
    ).apply {
        zIndex = 111
        iconTintColor = Color.parseColor("#FA295B")
        width = 100
        height = 125
    }

    private fun Marker.setMarkerIconAndColor(/*category: RestaurantCategory*/) {
//        when (category) {
//            RestaurantCategory.ALL -> {
//                icon = OverlayImage.fromResource(R.drawable.marker_m)
//                iconTintColor = Color.parseColor("#46F5FF")
//            }
//            RestaurantCategory.KOREAN_FOOD -> {
//                icon = OverlayImage.fromResource(R.drawable.marker_r)
//                iconTintColor = Color.parseColor("#FFCB41")
//            }
//            RestaurantCategory.DUMPLING_FOOD -> {
//                icon = OverlayImage.fromResource(R.drawable.marker_s)
//                iconTintColor = Color.parseColor("#886AFF")
//            }
//            RestaurantCategory.CAFE_DESSERT -> {
//                icon = OverlayImage.fromResource(R.drawable.marker_e)
//                iconTintColor = Color.parseColor("#04B404")
//            }
//            RestaurantCategory.JAPANESE_FOOD -> {
//                icon = OverlayImage.fromResource(R.drawable.marker_f)
//                iconTintColor = Color.parseColor("#8A0886")
//            }
//            else -> {
//                icon = OverlayImage.fromResource(R.drawable.marker_f)
//                iconTintColor = Color.parseColor("#0B2F3A")
//            }
//        }
    }
}