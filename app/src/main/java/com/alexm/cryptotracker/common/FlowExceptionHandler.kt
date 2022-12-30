package com.alexm.cryptotracker.common

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

val flowExceptionHandler = CoroutineExceptionHandler { _, _ ->
    Log.d("CryptoApp", "something went wrong")
}