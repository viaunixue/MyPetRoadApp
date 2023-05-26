package com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mju.capstone.mypetRoad.R

import com.mju.capstone.mypetRoad.databinding.WeeklylogAdapterItemBinding
import com.mju.capstone.mypetRoad.domain.model.WalkingLog

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
        holder.binding.walkingLogImage.setImageResource(R.drawable.sample_map_view)
        holder.binding.walkingLogDate.text = walkingLog.date
        holder.binding.walkingLogDistance.text = walkingLog.distance.toString()
        holder.binding.walkingLogKcal.text = walkingLog.calories.toString()
        holder.binding.walkingLogTime.text = walkingLog.time
        holder.itemView.setOnClickListener {
            // 세부 정보
        }
    }
}
