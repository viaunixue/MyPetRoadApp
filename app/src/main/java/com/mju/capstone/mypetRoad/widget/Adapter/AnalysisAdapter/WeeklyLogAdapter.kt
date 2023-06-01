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

    private var itemClickListener: OnItemClickListener? = null
    interface OnItemClickListener {
        //클릭시 동작할 함수
        fun onItemClick(v: View?, pos: Int)
    }
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

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        itemClickListener = listener
    }

    override fun onBindViewHolder(holder: WeeklyLogViewHolder, position: Int) {
        val walkingLog = walkingLogs[position]
        holder.binding.walkingLogImage.setImageResource(walkingLog.LogImage)
        holder.binding.walkingLogDate.text = walkingLog.date
        holder.binding.walkingLogDistance.text = walkingLog.distance.toString()
        holder.binding.walkingLogKcal.text = walkingLog.calories.toString()
        holder.binding.walkingLogTime.text = walkingLog.time
        holder.binding.walkingLogId.text = walkingLog.id.toString()

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(it, position)
        }
    }
}
