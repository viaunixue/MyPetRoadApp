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

    @Inject
    lateinit var navControllerProvider: Provider<NavController>
    // fragment 가 생성이 되고 fragment 가 있어야 navController 가
    // 그때 그 id 값을 반환 하면서 get 으로 가져옴
    private val navController get() = navControllerProvider.get()

    override fun getViewBinding() = FragmentAnalysisBinding.inflate(layoutInflater)

    private val analysisViewModel by viewModels<AnalysisViewModel>()

    override fun initViews() {
        super.initViews()

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.dailyButton -> navigateToDailyFragment()
                R.id.weeklyButton -> navigateToWeeklyFragment()
                R.id.monthlyButton -> navigateToMonthlyFragment()
            }
        }
    }

//    private fun addAnalysisFragment() {
//        childFragmentManager.beginTransaction()
//            .add(R.id.petroad_analysis_host_fragment, WeeklyFragment())
//            .setReorderingAllowed(true)
//            .addToBackStack(null)
//            .commit()
//    }

    private fun navigateToDailyFragment() {
        navController.navigate(R.id.dailyAnalysis)
    }

    private fun navigateToWeeklyFragment() {
        navController.navigate(R.id.weeklyAnalysis)
    }

    private fun navigateToMonthlyFragment() {
        navController.navigate(R.id.monthlyAnalysis)
    }
}