package com.alexm.cryptotracker.common

import retrofit2.HttpException
import java.io.IOException
import kotlin.Exception

object ErrorHandler {
    fun handleExceptionMessage(exception: Exception): String {
        return when (exception) {
            is IOException -> handleIOException()
            is HttpException -> handleHttpException(exception)
            else -> handleGlobalException()
        }
    }

    private fun handleHttpException(exception: HttpException): String {
        return when(exception.code()) {
            HttpError.ERROR_400.codeValue -> Constants.ERROR_400
            HttpError.ERROR_404.codeValue -> Constants.ERROR_404
            HttpError.ERROR_409.codeValue -> Constants.ERROR_400
            HttpError.ERROR_500.codeValue -> Constants.ERROR_500
            else -> Constants.GENERAL_ERROR_MESSAGE
        }
    }

    private fun handleIOException(): String {
        return Constants.LOST_INTERNET_CONNECTION
    }

    private fun handleGlobalException(): String {
        return Constants.GENERAL_ERROR_MESSAGE
    }
}

enum class HttpError(val codeValue: Int){
    ERROR_400(400),
    ERROR_404(404),
    ERROR_409(409),
    ERROR_500(500)
}