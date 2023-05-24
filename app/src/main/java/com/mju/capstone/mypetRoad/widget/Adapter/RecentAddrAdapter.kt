package com.mju.capstone.mypetRoad.widget.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mju.capstone.mypetRoad.data.dto.searchAddress.AddressHistoryDto
import com.mju.capstone.mypetRoad.databinding.RecyclerRecentAddrItemBinding

class RecentAddrAdapter(val itemClick: (AddressHistoryDto) -> Unit) :
    RecyclerView.Adapter<RecentAddrAdapter.ViewHolder>() {

    var datas = mutableListOf<AddressHistoryDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = RecyclerRecentAddrItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(b, itemClick)
    }
    fun clear() {
        datas.clear()
    }
    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }
    inner class ViewHolder(
        private val binding: RecyclerRecentAddrItemBinding,
        itemClick: (AddressHistoryDto) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: AddressHistoryDto) {
            binding.tvAddr.text = item.fullAddress
            binding.root.setOnClickListener { itemClick(item) }
        }
    }
}