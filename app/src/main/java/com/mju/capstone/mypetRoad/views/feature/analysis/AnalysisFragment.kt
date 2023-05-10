package com.mju.capstone.mypetRoad.views.feature.analysis

import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.databinding.FragmentAnalysisBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisFragment : BaseFragment<FragmentAnalysisBinding>() {
    override fun getViewBinding() = FragmentAnalysisBinding.inflate(layoutInflater)

    private val analysisViewModel by viewModels<AnalysisViewModel>()

}