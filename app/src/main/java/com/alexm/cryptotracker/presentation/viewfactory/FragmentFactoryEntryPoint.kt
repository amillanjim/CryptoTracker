package com.alexm.cryptotracker.presentation.viewfactory

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface FragmentFactoryEntryPoint {
    fun getFragmentFactory(): CryptoTrackerFragmentFactory
}