package com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mju.capstone.mypetRoad.R

import com.mju.capstone.mypetRoad.databinding.WeeklylogAdapterItemBinding
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
import com.mju.capstone.mypetRoad.views.feature.analysis.AnalysisDetailFragment
import com.mju.capstone.mypetRoad.views.feature.analysis.AnalysisFragmentDirections

//커스텀 리스너 인터페이스 정의


class WeeklyLogAdapter (
    private val walkingLogs : List<WalkingLog>
) : RecyclerView.Adapter<WeeklyLogAdapter.WeeklyLogViewHolder>() {
    inner class WeeklyLogViewHolder(val binding: WeeklylogAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyLogViewHolder {
        val binding = WeeklylogAdapterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeeklyLogViewHolder(binding)
    }

    override fun getItemCount(): Int = walkingLogs.size

    override fun onBindViewHolder(holder: WeeklyLogViewHolder, position: Int) {
        val walkingLog = walkingLogs[position]
        holder.binding.walkingLogImage.setImageResource(walkingLog.LogImage)
        holder.binding.walkingLogDate.text = walkingLog.date
        holder.binding.walkingLogDistance.text = walkingLog.distance.toString()
        holder.binding.walkingLogKcal.text = walkingLog.calories.toString()
        holder.binding.walkingLogTime.text = walkingLog.time

        holder.itemView.setOnClickListener {view ->
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_analysisFragment_to_analysisDetailFragment)
        }
    }
}
