package com.alexm.cryptotracker.common

object ErrorMessages {

    // Time Out or IO connection exceptions
    const val LOST_INTERNET_CONNECTION = "Verify that your phone is connected to the internet and" +
            " try again. If the problem continues, contact us."

    // 4xx: Client Error
    const val ERROR_400 = "Oh no… Something went wrong! We're solving the problem, try it later."
    const val ERROR_401 = "Oh no, it looks like your session has expired, log in and try again."
    const val ERROR_403 = "Oh no, it looks like you don't have the right permissions for this."
    const val ERROR_404 = "0ops! We didn't find what you were looking for, " +
            "let's see what happens, try again in another moment."

    // 5xx: Server Error
    const val ERROR_500 = "Something went wrong while loading your request, we are working to fix" +
            " it, come back later."
    const val ERR0R_502 = "We didn't able to load the information, we are working hard to fix" +
            " it, please try it later."
    const val ERROR_503 = "We are improving our services, so this information is disabled, " +
            "come back in a few minutes."
    const val ERR0R_504 = "Oops, we reach the timeout for this request, try again."

    // Unknown errors
    const val GENERAL_ERROR_MESSAGE = "A problem occurred, we are looking for it," +
            " try again later"
}