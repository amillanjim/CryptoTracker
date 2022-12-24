package com.alexm.cryptotracker.presentation.ui.activity

import android.view.LayoutInflater
import com.alexm.cryptotracker.base.BaseActivity
import com.alexm.cryptotracker.databinding.ActivityCryptoTrackerBinding
import com.alexm.cryptotracker.presentation.navigator.CryptoTrackerScreenNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CryptoTrackerActivity(
    override val bindingInflater: (LayoutInflater) ->
    ActivityCryptoTrackerBinding = ActivityCryptoTrackerBinding::inflate
): BaseActivity<ActivityCryptoTrackerBinding>(){

    @Inject
    lateinit var navigator: CryptoTrackerScreenNavigator

    override fun initView() = navigator.openSplashDialogFragment()
}