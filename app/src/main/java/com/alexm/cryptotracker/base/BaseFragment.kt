package com.alexm.cryptotracker.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

abstract class BaseFragment<VB: ViewBinding>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> VB
): Fragment(){

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    protected var fragmentJob: Job? = null

    protected var touchActionDelegate: TouchActionDelegate? = null

    open fun VB.initialize(){}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if(it is TouchActionDelegate) touchActionDelegate = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        binding.initialize()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    open fun initView(){}

    protected fun showAnimation(){}

    protected fun closeAnimation(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        fragmentJob?.cancelChildren()
    }

    interface TouchActionDelegate {
        fun showLoadingDialog(){}
        fun showErrorDialog(){}
        fun backButtonPressed(){}
    }
}