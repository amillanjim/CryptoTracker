package com.alexm.cryptotracker.presentation.navigator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.common.openFragment
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
        activity.openFragment(fragmentClass = AnimationDialogFragment::class.java)

    override fun openErrorDialogFragment() =
        activity.openFragment(fragmentClass = ErrorDialogFragment::class.java)

    override fun popFragment() = fragmentManager.popBackStack()

    override fun navigateToCryptoTracker() =
        activity.openFragment(
            fragmentClass = CryptoTrackerFragment::class.java,
            replaceFragment = true
        )

    override fun navigateToCoinDetail(coinId: String?, isFavorite: Boolean) {
        val bundle = Bundle()
        bundle.putString(Constants.COIN_ID, coinId)
        bundle.putBoolean(Constants.IS_FAVORITE_COIN, isFavorite)
        activity.openFragment(
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
}