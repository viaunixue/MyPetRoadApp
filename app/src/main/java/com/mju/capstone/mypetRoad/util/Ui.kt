package com.mju.capstone.mypetRoad.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.domain.model.WalkingLog
import com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter.WeeklyLogAdapter

open class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // 첫 번째 아이템 간격
//            if(parent.getChildAdapterPosition(view) != 0){
//                outRect.top = verticalSpaceHeight
//            }
        outRect.bottom = verticalSpaceHeight
    }
}
