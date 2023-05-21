package com.mju.capstone.mypetRoad.views.feature.analysis

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentAnalysisBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class AnalysisFragment : BaseFragment<FragmentAnalysisBinding>() {
    override fun getViewBinding() = FragmentAnalysisBinding.inflate(layoutInflater)

    private val analysisViewModel by viewModels<AnalysisViewModel>()

    override fun initViews() {
        super.initViews()

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.dailyButton -> navigateToDailyFragment()
                R.id.weeklyButton -> navigateToWeeklyFragment()
                R.id.monthlyButton -> navigateToMonthlyFragment()
                R.id.entireButton -> navigateToEntireFragment()
            }
        }
    }

    private fun navigateToDailyFragment() {
        val fragment = DailyFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.analysis_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToWeeklyFragment() {
        val fragment = WeeklyFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.analysis_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToMonthlyFragment() {
        val fragment = MonthlyFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.analysis_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToEntireFragment() {
        val fragment = EntireFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.analysis_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}