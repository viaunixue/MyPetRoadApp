package com.mju.capstone.mypetRoad.views.feature.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mju.capstone.mypetRoad.util.Config
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel(){

    val petName = ObservableField<String>()
    val petAge = ObservableField<String>()
    val petIsWalked = ObservableField<String>()

    fun petInfoUpdateText() {
        val petNameValue = "${Config.pet.name}"
        val petAgeValue = "${Config.pet.age}살"
        petName.set(petNameValue)
        petAge.set(petAgeValue)

        if(Config.todayIsWalked)
            Config.todayIsWalkedString.set("오늘 산책 함")
        petIsWalked.set(Config.todayIsWalkedString.get())


    }

}