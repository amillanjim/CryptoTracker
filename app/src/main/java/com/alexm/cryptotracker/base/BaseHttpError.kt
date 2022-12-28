package com.alexm.cryptotracker.base

enum class BaseHttpError(val codeValue: Int) {
    ERROR_400(codeValue = 400),
    ERROR_401(codeValue = 401),
    ERROR_403(codeValue = 403),
    ERROR_404(codeValue = 404),
    ERROR_409(codeValue = 409),
    ERROR_500(codeValue = 500),
    ERROR_502(codeValue = 502),
    ERROR_503(codeValue = 503),
    ERROR_504(codeValue = 504),
}