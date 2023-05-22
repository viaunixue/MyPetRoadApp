package com.mju.capstone.mypetRoad.views.feature.walking

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.mju.capstone.mypetRoad.R
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentWalkingBinding
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.util.Distance
import com.mju.capstone.mypetRoad.util.Route
import com.mju.capstone.mypetRoad.views.MainActivity
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WalkingFragment : BaseFragment<FragmentWalkingBinding>() {
    override fun getViewBinding() =  FragmentWalkingBinding.inflate(layoutInflater)

}