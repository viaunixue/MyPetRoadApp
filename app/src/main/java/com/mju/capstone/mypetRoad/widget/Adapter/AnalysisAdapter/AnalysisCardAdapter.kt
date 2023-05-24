package com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter

import androidx.cardview.widget.CardView

interface AnalysisCardAdapter {

    fun getBaseElevation(): Float
    fun getCardViewAt(position: Int): CardView
    fun getCount(): Int

    companion object {
        const val MAX_ELEVATION_FACTOR = 8
    }
}