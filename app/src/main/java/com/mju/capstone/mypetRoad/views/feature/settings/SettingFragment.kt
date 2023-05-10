package com.mju.capstone.mypetRoad.views.feature.settings

import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.databinding.FragmentSettingBinding
import com.mju.capstone.mypetRoad.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun getViewBinding() = FragmentSettingBinding.inflate(layoutInflater)

    private val settingViewModel by viewModels<SettingViewModel>()

}