package com.mju.capstone.mypetRoad.views.feature.walking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalkingViewModel @Inject constructor(

): ViewModel(){
    val buttonText: MutableLiveData<String> = MutableLiveData("산책 시작")

    fun onButtonClick() {
        val currentText = buttonText.value
        if (currentText == "산책 시작") {
            buttonText.value = "산책 종료"
        } else {
            buttonText.value = "산책 시작"
        }
    }
}