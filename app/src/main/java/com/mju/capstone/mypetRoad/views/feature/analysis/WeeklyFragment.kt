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
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.util.DateFormatter
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
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7) // 현재 날짜로부터 7일 전 날짜로 설정
        val oneWeekAgo = calendar.time // 최근 일주일 전 날짜

        for(i in Config.walkList.reversed()){ //log add
            val dateStr = DateFormatter.dateToString(i.walkDate)!!.take(10)
            if (i.walkDate >= oneWeekAgo) {
                val min = i.activity.walkedTime.toLong() / 60
                val sec = i.activity.walkedTime.toLong() % 60
                val distance = String.format("%.2f", i.activity.travelDistance / 1000f).toFloat()
                walkingLogs.add(WalkingLog(R.drawable.sample_map_view, dateStr, distance, i.activity.burnedCalories, "$min:$sec"))
            }
        }
        weeklyLogAdapter.notifyDataSetChanged()
    }

}