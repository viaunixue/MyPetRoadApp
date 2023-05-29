package com.mju.capstone.mypetRoad.views.feature.analysis

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentWeeklyBinding
import com.mju.capstone.mypetRoad.domain.model.Date
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
import com.mju.capstone.mypetRoad.util.VerticalSpaceItemDecoration
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.WeeklyLogAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class WeeklyFragment : BaseFragment<FragmentWeeklyBinding>(){
    private val walkingLogs = mutableListOf<WalkingLog>()
    private lateinit var weeklyLogAdapter: WeeklyLogAdapter
    private val analysisViewModel by viewModels<AnalysisViewModel>()

    override fun getViewBinding() = FragmentWeeklyBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.weeklyCard.analysisViewModel = analysisViewModel //ViewModel설정
        analysisViewModel.weeklyUpdateText() //텍스트업뎃

        weeklyLogAdapter = WeeklyLogAdapter(walkingLogs)

        return binding.root
    }

    override fun initViews() {
        super.initViews()
        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)

        binding.walkingLogVerticalScroll.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = weeklyLogAdapter
            setWeeklyLogView()
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            addItemDecoration(VerticalSpaceItemDecoration(spacing))
        }
    }

    private fun setWeeklyLogView() {
        walkingLogs.add(
            WalkingLog(R.drawable.sample_map_view,
                "2023/05/17", 3F, 3, "10'55\"")
        )
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2023/05/17", 3F, 3, "10'55\""))
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2023/05/17", 3F, 3, "10'55\""))
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2023/05/17", 3F, 3, "10'55\""))
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2023/05/17", 3F, 3, "10'55\""))

        weeklyLogAdapter.notifyDataSetChanged()
    }

}