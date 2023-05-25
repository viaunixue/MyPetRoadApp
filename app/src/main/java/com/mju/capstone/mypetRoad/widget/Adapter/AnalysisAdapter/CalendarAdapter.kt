package com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.mju.capstone.mypetRoad.databinding.CalendarCellBinding
import com.mju.capstone.mypetRoad.domain.model.Date
import kotlin.collections.ArrayList

class CalendarAdapter (
    private val dateSet: ArrayList<Date>
):RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>(){
    var drawable: Drawable? = null
    private lateinit var itemClickListener: OnItemClickListener

    inner class CalendarViewHolder(val binding: CalendarCellBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = CalendarCellBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false
        )
        return CalendarViewHolder(binding)
    }

    override fun getItemCount(): Int = dateSet.size

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = dateSet[position]
        holder.binding.dateCell.text = date.date
        holder.binding.dayCell.text = date.day

        holder.itemView.setOnClickListener {
            // 세부 정보
        }
    }
}