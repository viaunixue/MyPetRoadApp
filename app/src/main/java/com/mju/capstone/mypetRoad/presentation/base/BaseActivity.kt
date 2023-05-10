package com.mju.capstone.mypetRoad.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mju.capstone.mypetRoad.data.api.RetrofitInstance

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    abstract fun getViewBinding(): VB
    var retrofitInstance = RetrofitInstance.service

    val binding by lazy {
        getViewBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initState()
    }

    open fun initState(){
        initViews()
        observeData()
    }

    open fun initViews() = Unit

    abstract fun observeData()
}