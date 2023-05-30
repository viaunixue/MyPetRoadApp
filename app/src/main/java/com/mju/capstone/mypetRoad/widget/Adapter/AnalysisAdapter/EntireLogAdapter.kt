package com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.mju.capstone.mypetRoad.databinding.EntirelogAdapterItemBinding
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
import com.mju.capstone.mypetRoad.views.feature.analysis.AnalysisFragmentDirections

class EntireLogAdapter(
    private val walkingLogs: List<WalkingLog>
) : RecyclerView.Adapter<EntireLogAdapter.EntireLogViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    //인터페이스 선언
    interface OnItemClickListener {
        //클릭시 동작할 함수
        fun onItemClick(v: View?, pos: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        itemClickListener = listener
    }

    inner class EntireLogViewHolder(val binding: EntirelogAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntireLogViewHolder {
        val binding = EntirelogAdapterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EntireLogViewHolder(binding)
    }

    override fun getItemCount(): Int = walkingLogs.size

    override fun onBindViewHolder(holder: EntireLogViewHolder, position: Int) {

        val walkingLog = walkingLogs[position]
    //            holder.binding.walkingLogImage.setImageResource(R.drawable.sample_map_view)
        holder.binding.walkingLogImage.setImageResource(walkingLog.LogImage)
        holder.binding.walkingLogDate.text = walkingLog.date
        holder.binding.walkingLogDistance.text = walkingLog.distance.toString()
        holder.binding.walkingLogKcal.text = walkingLog.calories.toString()
        holder.binding.walkingLogTime.text = walkingLog.time
//        holder.itemView.setOnClickListener {view ->
//            val action = AnalysisFragmentDirections.actionAnalysisFragmentToAnalysisDetailFragment()
//            navController.navigate(action)
        }
//        holder.itemView.setOnClickListener {view ->
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_analysisFragment_to_analysisDetailFragment)
//        }
    }