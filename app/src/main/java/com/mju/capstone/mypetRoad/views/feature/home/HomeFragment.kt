package com.mju.capstone.mypetRoad.views.feature.home

import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.databinding.FragmentHomeBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    private val homeViewModel by viewModels<HomeViewModel>()

}