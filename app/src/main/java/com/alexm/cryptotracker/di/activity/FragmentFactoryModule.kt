package com.alexm.cryptotracker.di.activity

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.alexm.cryptotracker.di.annotation.FragmentKey
import com.alexm.cryptotracker.presentation.ui.dialog.AnimationDialogFragment
import com.alexm.cryptotracker.presentation.ui.fragment.CoinDetailFragment
import com.alexm.cryptotracker.presentation.ui.fragment.CryptoTrackerFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class FragmentFactoryModule {

    @Binds
    @IntoMap
    @FragmentKey(CryptoTrackerFragment::class)
    abstract fun bindCryptoTrackerFragment(fragment: CryptoTrackerFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(CoinDetailFragment::class)
    abstract fun bindCoinDetailFragment(fragment: CoinDetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(AnimationDialogFragment::class)
    abstract fun bindLoadingDialogFragment(fragment: AnimationDialogFragment): DialogFragment
}