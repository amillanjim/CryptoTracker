package com.alexm.cryptotracker.base

import com.alexm.cryptotracker.common.ErrorMessages
import retrofit2.HttpException
import java.io.IOException
import kotlin.Exception

object BaseErrorHandler {
    fun handleExceptionMessage(exception: Exception): String {
        return when (exception) {
            is IOException -> handleIOException()
            is HttpException -> handleHttpException(exception)
            else -> handleGlobalException()
        }
    }

    private fun handleHttpException(exception: HttpException): String {
        return when(exception.code()) {
            BaseHttpError.ERROR_400.codeValue -> ErrorMessages.ERROR_400
            BaseHttpError.ERROR_401.codeValue -> ErrorMessages.ERROR_401
            BaseHttpError.ERROR_403.codeValue -> ErrorMessages.ERROR_403
            BaseHttpError.ERROR_404.codeValue -> ErrorMessages.ERROR_404
            BaseHttpError.ERROR_409.codeValue -> ErrorMessages.ERROR_400
            BaseHttpError.ERROR_500.codeValue -> ErrorMessages.ERROR_500
            BaseHttpError.ERROR_502.codeValue -> ErrorMessages.ERR0R_502
            BaseHttpError.ERROR_503.codeValue -> ErrorMessages.ERROR_503
            BaseHttpError.ERROR_504.codeValue -> ErrorMessages.ERR0R_504
            else -> ErrorMessages.GENERAL_ERROR_MESSAGE
        }
    }

    private fun handleIOException(): String {
        return ErrorMessages.LOST_INTERNET_CONNECTION
    }

    private fun handleGlobalException(): String {
        return ErrorMessages.GENERAL_ERROR_MESSAGE
    }
}