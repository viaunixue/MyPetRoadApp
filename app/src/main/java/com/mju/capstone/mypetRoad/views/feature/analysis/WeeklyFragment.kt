package com.mju.capstone.mypetRoad.views.feature.analysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentDailyBinding
import com.mju.capstone.mypetRoad.databinding.FragmentWeeklyBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WeeklyFragment : BaseFragment<FragmentWeeklyBinding>() {

    override fun getViewBinding() = FragmentWeeklyBinding.inflate(layoutInflater)

}