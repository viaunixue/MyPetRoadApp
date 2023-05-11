package com.mju.capstone.mypetRoad.views.feature.map.searchAddress

import androidx.annotation.StringRes
import com.mju.capstone.mypetRoad.data.dto.searchAddress.MapSearchInfoDto

sealed class SearchState {

    object Uninitialized : SearchState()
    object Loading : SearchState()

    data class Success(
        val mapSearchInfoDto: MapSearchInfoDto
//        val foodMenuListInBasket: List<RestaurantFoodEntity>? = null
    ) : SearchState()

    data class Error(
        @StringRes val errorMessage: Int
    ) : SearchState()
}