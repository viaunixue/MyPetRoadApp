package com.mju.capstone.mypetRoad.views.feature.analysis

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentWeeklyBinding
import com.mju.capstone.mypetRoad.domain.model.Date
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
import com.mju.capstone.mypetRoad.util.VerticalSpaceItemDecoration
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.CalendarAdapter
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.WeeklyLogAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class WeeklyFragment : BaseFragment<FragmentWeeklyBinding>(){
    private val itemList = arrayListOf<Date>()
    private val walkingLogs = mutableListOf<WalkingLog>()
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var weeklyLogAdapter: WeeklyLogAdapter

    override fun getViewBinding() = FragmentWeeklyBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calendarAdapter = CalendarAdapter(itemList)
        weeklyLogAdapter = WeeklyLogAdapter(walkingLogs)

        return binding.root
    }

    override fun initViews() {
        super.initViews()
        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)

        binding.calendarList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = calendarAdapter
            setCalendarListView()
        }

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

    private fun setCalendarListView() {
        val lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
        val lastDayOfMonthFormatted = lastDayOfMonth.format(DateTimeFormatter.ofPattern("dd"))

        for (i in 1..lastDayOfMonthFormatted.toInt()) {
            val date = LocalDate.of(LocalDate.now().year, LocalDate.now().month, i)
            val dayOfWeek: DayOfWeek = date.dayOfWeek
            val dayOfWeekFormatted = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US)

            itemList.add(Date(dayOfWeekFormatted.substring(0, 3), i.toString()))
        }
        calendarAdapter.notifyDataSetChanged()
    }

}