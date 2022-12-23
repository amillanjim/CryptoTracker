package com.alexm.cryptotracker.presentation.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.alexm.cryptotracker.base.BaseActivity
import com.alexm.cryptotracker.base.BaseDialogFragment
import com.alexm.cryptotracker.base.BaseFragment
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.common.addFragment
import com.alexm.cryptotracker.common.replaceFragment
import com.alexm.cryptotracker.databinding.ActivityCryptoTrackerBinding
import com.alexm.cryptotracker.presentation.ui.dialog.AnimationDialogFragment
import com.alexm.cryptotracker.presentation.ui.dialog.ErrorDialogFragment
import com.alexm.cryptotracker.presentation.ui.fragment.CoinDetailFragment
import com.alexm.cryptotracker.presentation.ui.fragment.CryptoTrackerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoTrackerActivity(
    override val bindingInflater: (LayoutInflater) ->
    ActivityCryptoTrackerBinding = ActivityCryptoTrackerBinding::inflate
): BaseActivity<ActivityCryptoTrackerBinding>(), BaseFragment.TouchActionDelegate,
    BaseDialogFragment.DismissListener{

    override fun initView() = showSplashScreenDialog()

    private fun showSplashScreenDialog() =
        openFragment(
            fragmentClass = AnimationDialogFragment::class.java,
            dialogViewFlow =  Constants.SPLASH_SCREEN
        )

    override fun openCryptoFragment() =
        openFragment(
            fragmentClass = CryptoTrackerFragment::class.java,
            replaceFragment = true
        )

    override fun openDetailFragment() =
        openFragment(
            fragmentClass = CoinDetailFragment::class.java,
            replaceFragment = true
        )

    override fun showLoadingDialog() =
        openFragment(
            fragmentClass = AnimationDialogFragment::class.java,
            dialogViewFlow = Constants.LOADING_DIALOG
        )

    override fun showErrorDialog() {
        openFragment(
            fragmentClass = ErrorDialogFragment::class.java,
            replaceFragment = false
        )
    }

    private fun openFragment(
        fragmentClass: Class<out Fragment>,
        dialogViewFlow: String = "",
        replaceFragment: Boolean = false){
        var bundle: Bundle? = null

        if (dialogViewFlow.isNotEmpty()) {
            bundle = Bundle()
            bundle.putString(Constants.DIALOG_BUNDLE, dialogViewFlow)
        }

        if (replaceFragment) {
            supportFragmentManager.popBackStack()
            replaceFragment(
                fragment = fragmentClass,
                tag = fragmentClass.canonicalName,
                backStack = fragmentClass.canonicalName,
                bundle = bundle
            )
        } else {
            addFragment(
                fragment = fragmentClass,
                tag = fragmentClass.canonicalName,
                backStack = fragmentClass.canonicalName,
                bundle = bundle
            )
        }
    }
}