package com.mju.capstone.mypetRoad.views.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitInstance

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    abstract fun getViewBinding(): VB
    abstract fun observeData()

//    var retrofitInstance = RetrofitInstance.service
//    private val serverInstance = RetrofitInstance.serverService

    protected val binding by lazy {
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
}