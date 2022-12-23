package com.alexm.cryptotracker.common

sealed class Resource<T>(val data: T? = null, val message: String? = null, code: Int? = null){
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null, code: Int? = null):
        Resource<T>(data, message, code)
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Empty<T>: Resource<T>()
}