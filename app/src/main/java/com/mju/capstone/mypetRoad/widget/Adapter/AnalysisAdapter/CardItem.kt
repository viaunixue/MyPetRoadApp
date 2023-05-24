package com.mju.capstone.mypetRoad.widget.Adapter.AnalysisAdapter

class CardItem {

    private var mTextResource: String
    private var mTitleResource : String

    constructor(title: String, text: String) {
        mTitleResource = title
        mTextResource = text
    }

    fun getText(): String {
        return mTextResource
    }

    fun getTitle(): String {
        return mTitleResource
    }
}