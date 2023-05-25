package com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.MyWalkingAdapterItemBinding
import com.mju.capstone.mypetRoad.domain.model.MyWalking

class MyWalkingAdapter(
    private val myWalkings : List<MyWalking>
) : RecyclerView.Adapter<MyWalkingAdapter.EntireWalkingViewHolder>(){

    inner class EntireWalkingViewHolder(val binding: MyWalkingAdapterItemBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntireWalkingViewHolder {
        val binding = MyWalkingAdapterItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)

        return EntireWalkingViewHolder(binding)
    }

    override fun getItemCount(): Int = myWalkings.size

    override fun onBindViewHolder(holder: EntireWalkingViewHolder, position: Int) {
        val myWalking = myWalkings[position]
        holder.binding.myWalkingImage.setImageResource(R.drawable.sample_map_view)
        holder.binding.myWalkingDay.text = myWalking.date

        holder.itemView.setOnClickListener {
            // 세부 정보
        }
    }
}