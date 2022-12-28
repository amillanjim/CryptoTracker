package com.alexm.cryptotracker.presentation.ui.dialog

import androidx.fragment.app.FragmentManager
import com.alexm.cryptotracker.base.BaseDialogFragment
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.common.ErrorMessages
import com.alexm.cryptotracker.databinding.DialogFragmentErrorBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ErrorDialogFragment: BaseDialogFragment<DialogFragmentErrorBinding>
    (DialogFragmentErrorBinding::inflate) {

    @Inject
    lateinit var manager: FragmentManager

    override fun initView() {
        val errorMessage = arguments?.getString(Constants.DIALOG_ERROR_MESSAGE)
        binding.tvErrorMessage.text = errorMessage ?: ErrorMessages.GENERAL_ERROR_MESSAGE
        binding.btnError.setOnClickListener {
            if (manager.backStackEntryCount > 0 || manager.backStackEntryCount == 1) {
                requireActivity().supportFragmentManager.popBackStack()
            } else {
                requireActivity().supportFragmentManager.popBackStack()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
}