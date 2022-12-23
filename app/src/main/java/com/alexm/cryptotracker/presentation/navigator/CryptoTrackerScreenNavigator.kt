package com.alexm.cryptotracker.presentation.navigator

interface CryptoTrackerScreenNavigator {
    fun navigateToCoinDetail(coinId: String)
    fun navigateBack(backStackName: String? = null)
}