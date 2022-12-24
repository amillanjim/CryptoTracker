package com.alexm.cryptotracker.presentation.navigator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.common.addFragment
import com.alexm.cryptotracker.common.replaceFragment
import com.alexm.cryptotracker.presentation.ui.dialog.AnimationDialogFragment
import com.alexm.cryptotracker.presentation.ui.dialog.ErrorDialogFragment
import com.alexm.cryptotracker.presentation.ui.fragment.CoinDetailFragment
import com.alexm.cryptotracker.presentation.ui.fragment.CryptoTrackerFragment
import javax.inject.Inject

class CryptoTrackerScreenNavigatorImpl @Inject constructor(
    private val activity: AppCompatActivity,
    private val fragmentManager: FragmentManager
): CryptoTrackerScreenNavigator {
    override fun openSplashDialogFragment() =
        openFragment(fragmentClass = AnimationDialogFragment::class.java)

    override fun openErrorDialogFragment() =
        openFragment(fragmentClass = ErrorDialogFragment::class.java)

    override fun popFragment() = fragmentManager.popBackStack()

    override fun navigateToCryptoTracker() =
        openFragment(
            fragmentClass = CryptoTrackerFragment::class.java,
            replaceFragment = true
        )

    override fun navigateToCoinDetail(coinId: String?, isFavorite: Boolean) {
        val bundle = Bundle()
        bundle.putString(Constants.COIN_ID, coinId)
        bundle.putBoolean(Constants.IS_FAVORITE_COIN, isFavorite)
        openFragment(
            fragmentClass = CoinDetailFragment::class.java,
            bundle = bundle
        )
    }

    override fun navigateBack(backStackName: String?) {
        if (fragmentManager.backStackEntryCount == 1 ||
                fragmentManager.backStackEntryCount == 0) activity.finish()
        else backStackName?.let {
            fragmentManager.popBackStack(it, 0)
        } ?: fragmentManager.popBackStack()
    }

    private fun openFragment(
        fragmentClass: Class<out Fragment>,
        replaceFragment: Boolean = false,
        bundle: Bundle? = null){
        if (replaceFragment) {
            fragmentManager.popBackStack()
            activity.replaceFragment(
                fragment = fragmentClass,
                tag = fragmentClass.canonicalName,
                backStack = fragmentClass.canonicalName,
                bundle = bundle
            )
        } else {
            activity.addFragment(
                fragment = fragmentClass,
                tag = fragmentClass.canonicalName,
                backStack = fragmentClass.canonicalName,
                bundle = bundle
            )
        }
    }
}