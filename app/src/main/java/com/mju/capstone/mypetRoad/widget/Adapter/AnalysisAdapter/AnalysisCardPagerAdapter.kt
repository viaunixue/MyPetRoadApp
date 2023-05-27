package com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.databinding.AnalysisDailyItemBinding
import com.mju.capstone.mypetRoad.domain.model.CardItem
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.AnalysisCardAdapter.Companion.MAX_ELEVATION_FACTOR

class AnalysisCardPagerAdapter(
    val context: Context
): AnalysisCardAdapter, PagerAdapter() {
    private var cardViews: MutableList<CardView> = mutableListOf()
    private var cardData: MutableList<CardItem> = mutableListOf()
    private lateinit var binding : AnalysisDailyItemBinding
    private var mBaseElevation = 0f

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView {
        return cardViews[position]
    }

    fun addCardItem(item: CardItem) {
        cardData.add(item)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = AnalysisDailyItemBinding.inflate(inflater)
        binding.analysisCardMap.setImageResource(cardData[position].analysisCardMap)
        binding.analysisCardTime.text = cardData[position].analysisCardTime
        binding.analysisCardDistance.text = cardData[position].analysisCardDistance.toString()
        binding.analysisCardCalories.text = cardData[position].analysisCardCalories

//        binding.contextText.text = mData[position].getText()

        binding.analysisCardView.maxCardElevation = mBaseElevation * MAX_ELEVATION_FACTOR

        if (mBaseElevation == 0f) {
            mBaseElevation = binding.analysisCardView.cardElevation
        }

        binding.analysisCardView.maxCardElevation = mBaseElevation * MAX_ELEVATION_FACTOR

        cardViews.add(binding.analysisCardView)
        container.addView(binding.root)

        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return cardData.size
    }

    fun getRegisteredView(position: Int): CardView? {
        return cardViews[position]
    }

    inner class AnalysisCardViewHolder(val binding: AnalysisDailyItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}