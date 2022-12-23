package com.alexm.cryptotracker.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB: ViewBinding>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : DialogFragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    protected var dismissListener: DismissListener? = null

    open fun VB.initialize(){}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if(it is DismissListener) dismissListener = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        initView()
    }

    open fun initView(){}

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.onDismiss()
        _binding = null
    }

    override fun dismiss() {
        dismissAllowingStateLoss()
    }

    interface DismissListener {
        fun onDismiss(){}
        fun openCryptoFragment(){}
        fun openDetailFragment(){}
    }
}