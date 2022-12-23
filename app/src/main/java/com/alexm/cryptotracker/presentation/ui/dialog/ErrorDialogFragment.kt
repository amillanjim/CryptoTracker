package com.alexm.cryptotracker.presentation.ui.dialog

import com.alexm.cryptotracker.base.BaseDialogFragment
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.databinding.DialogFragmentErrorBinding

class ErrorDialogFragment: BaseDialogFragment<DialogFragmentErrorBinding>
    (DialogFragmentErrorBinding::inflate) {

    override fun initView() {
        val errorMessage = arguments?.getString(Constants.DIALOG_ERROR_MESSAGE)
        binding.tvErrorMessage.text = errorMessage ?: Constants.GENERAL_ERROR_MESSAGE
        binding.btnError.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}