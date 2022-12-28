package com.alexm.cryptotracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CryptoTrackerActivityViewModel @Inject constructor(): ViewModel() {

    private val _trackerFragmentHasBeenLaunch = MutableStateFlow(false)
    val trackerFragmentHasBeenLaunch: StateFlow<Boolean> = _trackerFragmentHasBeenLaunch

    fun updateTrackerFragmentState(){
        _trackerFragmentHasBeenLaunch.value = true
    }
}