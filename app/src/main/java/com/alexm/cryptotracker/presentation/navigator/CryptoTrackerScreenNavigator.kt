package com.alexm.cryptotracker.presentation.navigator

interface CryptoTrackerScreenNavigator {
    fun openSplashDialogFragment()
    fun openErrorDialogFragment()
    fun popFragment()
    fun navigateToCryptoTracker()
    fun navigateToCoinDetail(coinId: String?, isFavorite: Boolean)
    fun navigateBack(backStackName: String? = null)
}