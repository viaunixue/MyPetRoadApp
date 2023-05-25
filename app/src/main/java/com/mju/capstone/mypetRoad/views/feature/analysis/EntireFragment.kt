package com.mju.capstone.mypetRoad.views.feature.analysis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.FragmentEntireBinding
import com.mju.capstone.mypetRoad.domain.model.MyWalking
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
import com.mju.capstone.mypetRoad.util.VerticalSpaceItemDecoration
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.MyWalkingAdapter
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.WeeklyLogAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class EntireFragment : BaseFragment<FragmentEntireBinding>() {
    private val mapLogLists = mutableListOf<MyWalking>()
    private val walkingLogs = mutableListOf<WalkingLog>()
    private lateinit var myWalkingAdapter: MyWalkingAdapter
    private lateinit var entireLogAdapter: WeeklyLogAdapter
    override fun getViewBinding() = FragmentEntireBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myWalkingAdapter = MyWalkingAdapter(mapLogLists)
        entireLogAdapter = WeeklyLogAdapter(walkingLogs)
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
            setWeeklyLogView()
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
            addItemDecoration(VerticalSpaceItemDecoration(spacing))
        }
    }

    private fun setWeeklyLogView() {
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2023/05/17", "수요일 오후 산책", 3.67, "10'55\"", "12:08"))
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2022/04/20", "화요일 오전 산책", 2.67, "20'44\"", "10:08"))
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2021/03/23", "월요일 새벽 산책", 1.67, "30'33\"", "08:08"))
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2020/02/26", "일요일 오후 산책", 0.67, "40'22\"", "06:08"))
        walkingLogs.add(WalkingLog(R.drawable.sample_map_view, "2019/01/29", "토요일 오전 산책", 0.07, "50'11\"", "04:08"))

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