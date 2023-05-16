package com.mju.capstone.mypetRoad.views.feature.analysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentAnalysisBinding
import com.mju.capstone.mypetRoad.databinding.FragmentMonthlyBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MonthlyFragment : BaseFragment<FragmentMonthlyBinding>() {

    override fun getViewBinding() = FragmentMonthlyBinding.inflate(layoutInflater)

}