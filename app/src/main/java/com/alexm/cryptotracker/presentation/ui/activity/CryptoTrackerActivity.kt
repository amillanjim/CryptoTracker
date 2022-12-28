package com.alexm.cryptotracker.presentation.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.alexm.cryptotracker.base.BaseActivity
import com.alexm.cryptotracker.databinding.ActivityCryptoTrackerBinding
import com.alexm.cryptotracker.presentation.navigator.CryptoTrackerScreenNavigator
import com.alexm.cryptotracker.presentation.viewmodel.CryptoTrackerActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CryptoTrackerActivity(
    override val bindingInflater: (LayoutInflater) ->
    ActivityCryptoTrackerBinding = ActivityCryptoTrackerBinding::inflate
): BaseActivity<ActivityCryptoTrackerBinding>(){

    @Inject
    lateinit var navigator: CryptoTrackerScreenNavigator

    private val trackerActivityViewModel: CryptoTrackerActivityViewModel by viewModels()

    override fun initView() {
        val trackerActivityHasBeenLaunch = trackerActivityViewModel.trackerFragmentHasBeenLaunch.value
        if (trackerActivityHasBeenLaunch){
            navigator.navigateToCryptoTracker()
        } else {
            navigator.openSplashDialogFragment()
            trackerActivityViewModel.updateTrackerFragmentState()
        }
    }
}