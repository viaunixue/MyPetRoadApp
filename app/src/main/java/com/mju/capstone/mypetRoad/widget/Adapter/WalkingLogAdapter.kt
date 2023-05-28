package com.mju.capstone.mypetRoad.widget.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.WalkinglogAdapterItemBinding
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
import de.hdodenhof.circleimageview.CircleImageView

class WalkingLogAdapter (
   private val walkingLogs : List<WalkingLog>
) : RecyclerView.Adapter<WalkingLogAdapter.WalkingLogViewHolder>() {

    inner class WalkingLogViewHolder(val binding: WalkinglogAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalkingLogViewHolder {
        val binding = WalkinglogAdapterItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WalkingLogViewHolder(binding)
    }

    override fun getItemCount(): Int = walkingLogs.size

    override fun onBindViewHolder(holder: WalkingLogViewHolder , position: Int) {
        val walkingLog = walkingLogs[position]
//        holder.binding.walkingLogImage.setImageResource(R.drawable.sample_map_view)
        holder.binding.walkingLogImage.setImageResource(walkingLog.LogImage)
        holder.binding.walkingLogDate.text = walkingLog.date
        holder.binding.walkingLogContent.text = walkingLog.content
        holder.binding.walkingLogDistance.text = walkingLog.distance.toString()
        holder.binding.walkingLogKm.text = "km"
        holder.binding.walkingLogLab.text = walkingLog.lab
        holder.binding.walkingLogPace.text = "Avg.Pace"
        holder.binding.walkingLogTime.text = walkingLog.time
        holder.binding.walkingLogTimeText.text = "Time"

        holder.itemView.setOnClickListener {
            // 세부 정보
        }
    }
}
