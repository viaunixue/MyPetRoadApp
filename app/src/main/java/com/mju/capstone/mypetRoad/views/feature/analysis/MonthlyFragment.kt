package com.mju.capstone.mypetRoad.views.feature.analysis

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentMonthlyBinding
import com.mju.capstone.mypetRoad.util.DateFormatter.formatDate
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MonthlyFragment : BaseFragment<FragmentMonthlyBinding>() {
    val dummyData = arrayOf("2017,03,18", "2017,04,18", "2017,05,18", "2017,06,18")
    override fun getViewBinding() = FragmentMonthlyBinding.inflate(layoutInflater)
    private val analysisViewModel by viewModels<AnalysisViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.monthlyCard.analysisViewModel = analysisViewModel //ViewModel설정
        analysisViewModel.monthlyUpdateText()  //텍스트업뎃

        return binding.root
    }

    override fun initViews() {
        super.initViews()
        val calendarView = binding.calendarFrame

        // 한국어 변환
        calendarView.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        calendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

        // Header text 세팅
        calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader)

        // 산책 날짜 커스텀
        calendarView.addDecorators(SundayDayDecorator(requireContext()))
        calendarView.addDecorators(SaturdayDayDecorator(requireContext()))
        calendarView.addDecorators(EventDecorator(requireContext()))

        // 산책 상세 페이지 이동
        calendarView.setOnDateChangedListener { _, date, _ ->
            val selectedDate = date.calendar
            val formattedDate = formatDate(selectedDate)
            val bundle = bundleOf("selectedDate" to formattedDate)

            val navController = findNavController()
            val graph = navController.navInflater.inflate(R.navigation.monthly_nav_graph)
            navController.graph = graph

            view?.let { analysisMode ->
                Navigation.findNavController(analysisMode)
                    .navigate(R.id.action_analysisFragment_to_analysisDetailFragment, bundle)
            }
        }
    }

    private inner class EventDecorator(private val context: Context) : DayViewDecorator {
        private val drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.calendar_stamp)!!
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            val walkingDates = listOf("2023-05-28", "2023-05-15", "2023-05-05")
            val dateString = day?.date.toString()
            return walkingDates.contains(dateString)
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setSelectionDrawable(drawable)
//            view.addSpan(StyleSpan(Typeface.BOLD))
//            view.addSpan(RelativeSizeSpan(1.4f))
//            view.addSpan(ForegroundColorSpan(Color.RED))
        }

    }

    private inner class SaturdayDayDecorator(private val context: Context) : DayViewDecorator {

        private val calendar = Calendar.getInstance()
        override fun shouldDecorate(day: CalendarDay): Boolean {
            day.copyTo(calendar)
            val weekDay: Int = calendar.get(Calendar.DAY_OF_WEEK)
            return weekDay == Calendar.SATURDAY
        }

        // Apply the custom drawable when a day is selected
        override fun decorate(view: DayViewFacade) {
            view.addSpan(ForegroundColorSpan(Color.parseColor("#87CEFA")))
        }
    }

    private inner class SundayDayDecorator(private val context: Context) : DayViewDecorator {

//        private val drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector)!!
        private val calendar = Calendar.getInstance()
        override fun shouldDecorate(day: CalendarDay): Boolean {
            day.copyTo(calendar)
            val weekDay: Int = calendar.get(Calendar.DAY_OF_WEEK)
            return weekDay == Calendar.SUNDAY
        }
        override fun decorate(view: DayViewFacade) {
            view.addSpan(ForegroundColorSpan(Color.parseColor("#ff4040")))
        }
    }
}