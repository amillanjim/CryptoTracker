package com.alexm.cryptotracker.common

import android.util.Log
import com.alexm.cryptotracker.base.BaseErrorHandler
import kotlinx.coroutines.CoroutineExceptionHandler

val flowExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    val error = BaseErrorHandler.handleExceptionMessage(exception = throwable as Exception)
    Log.d("CryptoApp", error)
}