package com.mju.capstone.mypetRoad.views.feature.walking

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.data.retrofit.RetrofitManager
import com.mju.capstone.mypetRoad.databinding.FragmentWalkingBinding
import com.mju.capstone.mypetRoad.util.Config
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WalkingFragment : BaseFragment<FragmentWalkingBinding>() {
    override fun getViewBinding() =  FragmentWalkingBinding.inflate(layoutInflater)
    private var isChildFragmentCreated = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isChildFragmentCreated) {
            createChildFragment()
            isChildFragmentCreated = true
        }
    }

    private fun createChildFragment() {
        // WalkingHomeFragment 생성
        val walkingHomeFragment = WalkingHomeFragment()
        childFragmentManager.beginTransaction()
            .add(R.id.walking_host_fragment, walkingHomeFragment)
            .commit()
    }
}