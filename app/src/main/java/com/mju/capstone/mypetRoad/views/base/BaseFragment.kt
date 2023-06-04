package com.mju.capstone.mypetRoad.views.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.mju.capstone.mypetRoad.R
import com.mju.capstone.mypetRoad.util.Config
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB: ViewBinding>: Fragment() {
    private var _binding: VB? = null
    protected val binding : VB get() = _binding!!

    lateinit var progressDialog: Dialog

    var uiState: MutableStateFlow<UiState<Any>> = MutableStateFlow(UiState.Uninitialized)

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()

        progressDialog = Dialog(requireContext())
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.setCancelable(false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _binding = getViewBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initState()
        Log.e("e", "OnView")
    }

    open fun initState() {
        initViews()
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLowMemory() {
        super.onLowMemory()
        initState()
        Log.e("e", "OnLow")
    }

    open fun initViews() = Unit
    open fun observeData() = Unit

}