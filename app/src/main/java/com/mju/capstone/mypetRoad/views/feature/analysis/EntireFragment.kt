package com.mju.capstone.mypetRoad.views.feature.analysis

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentEntireBinding
import com.mju.capstone.mypetRoad.domain.model.MyWalking
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.util.DateFormatter
import com.mju.capstone.mypetRoad.util.VerticalSpaceItemDecoration
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.MyWalkingAdapter
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.WeeklyLogAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.Math.floor

@ExperimentalCoroutinesApi
@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class EntireFragment : BaseFragment<FragmentEntireBinding>() {
    private val mapLogLists = mutableListOf<MyWalking>()
    private val walkingLogs = mutableListOf<WalkingLog>()
    private lateinit var myWalkingAdapter: MyWalkingAdapter
    private lateinit var entireLogAdapter: WeeklyLogAdapter
    private val analysisViewModel by viewModels<AnalysisViewModel>()
    override fun getViewBinding() = FragmentEntireBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myWalkingAdapter = MyWalkingAdapter(mapLogLists)
        entireLogAdapter = WeeklyLogAdapter(walkingLogs)

        binding.topCard.analysisViewModel = analysisViewModel //ViewModel설정
        analysisViewModel.entireUpdateText()  //텍스트업뎃

        return binding.root
    }
    override fun initViews() {
        super.initViews()
        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)

        binding.myWalkingLog.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = myWalkingAdapter
            setMyWalkingLogView()
            addItemDecoration(VerticalSpaceItemDecoration(spacing))
        }

        binding.entireWalkingLog.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = entireLogAdapter
            setEntireLogView()
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
            addItemDecoration(VerticalSpaceItemDecoration(spacing))
        }
    }

    private fun setEntireLogView() {
        for(i in Config.walkList){ //log add
            val dateStr = DateFormatter.dateToString(i.walkDate)!!.take(10)
            val min = i.activity.walkedTime.toLong() / 60
            val sec = i.activity.walkedTime.toLong() % 60
            val distance = String.format("%.2f", i.activity.travelDistance/1000f).toFloat()
            walkingLogs.add(WalkingLog(R.drawable.sample_map_view, dateStr, distance, i.activity.burnedCalories, "$min:$sec"))
        }

        entireLogAdapter.notifyDataSetChanged()
    }

    private fun setMyWalkingLogView() {
        mapLogLists.add(MyWalking(R.drawable.sample_map_view, "2023/05/17"))
        mapLogLists.add(MyWalking(R.drawable.sample_map_view, "2023/05/16"))
        mapLogLists.add(MyWalking(R.drawable.sample_map_view, "2023/05/15"))
        mapLogLists.add(MyWalking(R.drawable.sample_map_view, "2023/05/14"))
        mapLogLists.add(MyWalking(R.drawable.sample_map_view, "2023/05/13"))

        myWalkingAdapter.notifyDataSetChanged()
    }
}