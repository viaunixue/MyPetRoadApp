package com.mju.capstone.mypetRoad.views.feature.map.searchAddress

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAddressViewModel @Inject constructor(
    private val addressApiRepositoryImpl: AddressApiRepositoryImpl
) : ViewModel() {

    private val _locationData = MutableLiveData<SearchState>(SearchState.Uninitialized)
    val locationData: LiveData<SearchState> = _locationData

    fun getAddressInformation(address: String) = viewModelScope.launch {

        val addressInfo = addressApiRepositoryImpl.getAddressInformation(address)

        addressInfo?.let { addressInfoResult ->

            Log.d("addressInfoResult", addressInfoResult.toString())

            _locationData.value = SearchState.Success(
                mapSearchInfoEntity = MapSearchInfoEntity(
                    fullAddress = addressInfoResult.documents[0].address_name ?: "주소 정보 없음",
                    name = addressInfoResult.documents[0].place_name ?: "주소 정보 없음",
                    locationLatLng = LocationEntity(
                        latitude = addressInfoResult.documents[0].y.toDouble(),
                        longitude = addressInfoResult.documents[0].x.toDouble()
                    )
                )
            )
        }
    }
}