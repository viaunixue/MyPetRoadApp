package com.mju.capstone.mypetRoad.views.feature.walking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mju.capstone.mypetRoad.databinding.FragmentWalkingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalkingFragment : Fragment() {
    private var _binding: FragmentWalkingBinding? = null
    private val binding get() = _binding!!

    private val walkingViewModel by viewModels<WalkingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWalkingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}