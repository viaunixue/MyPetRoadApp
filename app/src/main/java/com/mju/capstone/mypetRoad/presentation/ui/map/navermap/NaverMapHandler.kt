package com.mju.capstone.mypetRoad.presentation.ui.map.navermap

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay

typealias MarkerClickListener = Marker.(Overlay) -> Boolean

class NaverMapHandler(
    private val markerFactory: MarkerFactory,
    private val naverMap: NaverMap
) {

    /**
     * Handler의 지도에 띄울 마커들
     */
    private var markers = emptyList<Marker>()

    /**
     * 목적지 마커를 띄우는 함수
     */
    fun updateDestMarker(destMarker: Marker, location: LatLng) {
        destMarker.position = location
        destMarker.map = naverMap
    }

    /**
     * 카메라를 이동하는 함수
     * @param location 이동할 위치
     * @param onError 실패시 수행할 동작
     */

    /* onError: () -> Unit 까지의 람다식이 하나의 파라미터이다.
    ()->Unit의 의미는 파라미터가 없고, Unit 즉 아무것도 반환되지 않는 function
    마지막 파라미터가 람다식이면 중괄호 { } 부분을 소괄호 밖으로 뺄 수 있다. */

    fun moveCameraTo(location: LatLng, onError: () -> Unit) {
        try {
            naverMap.cameraPosition = CameraPosition(location, 15.0)
        } catch (ex: Exception) { onError() }
    }

    /**
     * Handler의 지도에서 마커들을 삭제
     */
    fun deleteMarkers() {
        for (marker in markers) {
            marker.map = null
        }
    }

    /**
     * Handler의 지도에 마커들을 표시
     */
    fun showMarkers() {
        for (marker in markers) {
            marker.map = naverMap
        }
    }

    /**
     * 주변 가게들에 대해 마커를 띄우는 함수
     * @param restaurantInfoList 마커를 띄울 가게 리스트
     */
//    fun updateRestaurantMarkers(
//        restaurantInfoList: List<RestaurantModel>,
//        clickListener: MarkerClickListener) {
//
//        deleteMarkers()
//
//        markers = restaurantInfoList.mapIndexed { index, restaurant ->
//            markerFactory.createMarker(
//                position = LatLng(restaurant.latitude, restaurant.longitude),
//                category = restaurant.restaurantCategory,
//                tag = restaurant,
//                zIndex = index
//            ).apply {
//                //Marker의 setOnClickListener
//                setOnClickListener { overlay -> clickListener(this, overlay) }
//            }
//
//        }
//        showMarkers()
//    }
}