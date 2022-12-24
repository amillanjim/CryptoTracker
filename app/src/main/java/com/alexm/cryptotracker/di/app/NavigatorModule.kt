package com.alexm.cryptotracker.di.app

import com.alexm.cryptotracker.presentation.navigator.CryptoTrackerScreenNavigator
import com.alexm.cryptotracker.presentation.navigator.CryptoTrackerScreenNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigatorModule {

    @Binds
    abstract fun bindCryptoTrackerScreenNavigator(navigator: CryptoTrackerScreenNavigatorImpl):
            CryptoTrackerScreenNavigator
}