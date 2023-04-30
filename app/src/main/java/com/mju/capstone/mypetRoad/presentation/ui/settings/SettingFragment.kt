package com.mju.capstone.mypetRoad.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.databinding.FragmentSettingBinding
import com.mju.capstone.mypetRoad.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun getViewBinding() = FragmentSettingBinding.inflate(layoutInflater)

    private val settingViewModel by viewModels<SettingViewModel>()

}