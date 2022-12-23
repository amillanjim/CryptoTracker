package com.alexm.cryptotracker.presentation.navigator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexm.cryptotracker.common.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoTrackerScreenNavigatorImpl @Inject constructor(
    //private val activity: AppCompatActivity
): CryptoTrackerScreenNavigator {
    override fun navigateToCoinDetail(coinId: String) {
        val bundle = Bundle()
        bundle.putString(Constants.COIN_ID, coinId)
        /*
        activity.addFragment(
            fragment = CoinDetailFragment::class.java,
            tag = CoinDetailFragment::class.java.canonicalName,
            backStack = CoinDetailFragment::class.java.canonicalName,
            bundle = bundle
        )*/
    }

    override fun navigateBack(backStackName: String?) {
        /*
        backStackName?.let {
            fragManager.popBackStack(it, 0)
        } ?: fragManager.popBackStack()*/
    }
}