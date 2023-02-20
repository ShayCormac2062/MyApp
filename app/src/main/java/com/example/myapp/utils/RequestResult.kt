package com.example.myapp.utils

sealed class RequestResult<T>(
    val data: T? = null,
    val exception: ApplicationException? = null
) {

    class Success<T>(data: T?) : RequestResult<T>(data)
    class Error<T>(
        message: ApplicationException,
        data: T? = null
    ) : RequestResult<T>(data, message)

}
