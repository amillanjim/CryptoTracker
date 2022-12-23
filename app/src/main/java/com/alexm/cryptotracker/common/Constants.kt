package com.alexm.cryptotracker.common

object Constants {
    const val BASE_URL = "https://api.coinpaprika.com/"

    const val PROFILE_IMAGE_URL =
        "https://www.shareicon.net/data/2015/09/20/104335_avatar_512x512.png"

    const val GENERIC_COIN_IMAGE = "https://cdn-icons-png.flaticon.com/512/922/922226.png"

    const val DATABASE_NAME = "crypto_tracker_db"

    const val COIN_ID = "coinId"

    const val TIME_OUT = 10L

    // Error values
    const val LOST_INTERNET_CONNECTION = "Verify that your phone is connected to the internet and" +
            " try again. If the problem continues, contact us."

    const val ERROR_400 = "Oh no… Something went wrong! Please try again in 5 minutes"
    const val ERROR_404 = "0ops! We didn't find what you were looking for, " +
            "let's see what happens, try again another time."
    const val ERROR_500 = "Come back in 5 minutes, we are looking for the problem"
    const val GENERAL_ERROR_MESSAGE = "problem occurred, we are fixing the problem" +
            " try again later"

    const val DIALOG_BUNDLE = "Dialog Bundle"
    const val LOADING_DIALOG = "Loading Dialog"
    const val SPLASH_SCREEN = "Splash Screen"

    const val DIALOG_ERROR_MESSAGE = "error message"
}