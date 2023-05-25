package com.mju.capstone.mypetRoad.views.feature.analysis

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentMonthlyBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.threeten.bp.DayOfWeek

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MonthlyFragment : BaseFragment<FragmentMonthlyBinding>() {

    private lateinit var calendarView: MaterialCalendarView
    override fun getViewBinding() = FragmentMonthlyBinding.inflate(layoutInflater)
    override fun initViews() {
        super.initViews()
        val calendarView = binding.calendarFrame

        //  첫 번째 날 일 -> 월로 변환
        calendarView.state().edit()
            .setFirstDayOfWeek(DayOfWeek.MONDAY)
            .commit()

        // 한국어 변환
        calendarView.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        calendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

        // Header text 세팅
        calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader)

        // 날짜 범위 세팅
        calendarView.setOnRangeSelectedListener { widget, dates ->
            val startDay = dates[0].toString()
            val endDay = dates[dates.size - 1].date.toString()
            Log.e(TAG, "시작일 : $startDay, 종료일 : $endDay")
        }

        // 선택한 날짜 커스텀
        calendarView.addDecorators(DayDecorator(requireContext()))

        // Header 타이틀 커스텀
        calendarView.setTitleFormatter { day ->
            val inputText = day?.date
            val calendarHeaderElements = inputText.toString().split("-")
            val calendarHeaderBuilder = StringBuilder()
            calendarHeaderBuilder.append(calendarHeaderElements[0])
                .append(" ")
                .append(calendarHeaderElements[1])
            calendarHeaderBuilder.toString()
        }
    }

    private inner class DayDecorator(private val context: Context) : com.prolificinteractive.materialcalendarview.DayViewDecorator {

        private val drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector)!!

        // Return true to decorate all days
        override fun shouldDecorate(day: CalendarDay): Boolean {
            return true
        }

        // Apply the custom drawable when a day is selected
        override fun decorate(view: DayViewFacade) {
            view.setSelectionDrawable(drawable)
//            view.addSpan(new StyleSpan(Typeface.BOLD)) // Uncomment this line to make all numbers in the calendar bold
        }
    }
}