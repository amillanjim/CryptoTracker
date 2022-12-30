package com.alexm.cryptotracker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alexm.cryptotracker.presentation.navigator.CryptoTrackerScreenNavigator
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import javax.inject.Inject

abstract class BaseFragment<VB: ViewBinding>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> VB
): Fragment(){

    @Inject
    lateinit var navigator: CryptoTrackerScreenNavigator

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    protected var fragmentJob: Job? = null

    open fun VB.initialize(){}

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
        addOnBackPressedCallback()
        initView()
    }

    private fun addOnBackPressedCallback(){
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    open fun initView(){}

    protected fun showAnimation(){}

    protected fun closeAnimation(){}

    open fun onBackPressed() = navigator.navigateBack()

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentJob?.cancelChildren()
    }
}