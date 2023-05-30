package com.mju.capstone.mypetRoad.views.feature.analysis

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentAnalysisBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class AnalysisFragment : BaseFragment<FragmentAnalysisBinding>() {
    override fun getViewBinding() = FragmentAnalysisBinding.inflate(layoutInflater)

//    private val analysisViewModel by viewModels<AnalysisViewModel>()

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

    override fun onCreateView( //Fragment의 onCreateView() 메서드에서 Child Fragment를 생성(첫 호출 에러 해결)
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //WalkList를 초기화함
        RetrofitManager.instance.getAllWalk()

        // Child fragment를 생성합니다.
        val childFragment = DailyFragment()

        // Child fragment를 추가합니다.
        childFragmentManager.beginTransaction()
            .add(R.id.analysis_host_fragment, childFragment)
            .commit()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun navigateToDailyFragment() {
        val fragment = DailyFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.analysis_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun navigateToEntireFragment() {
        val fragment = EntireFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.analysis_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}