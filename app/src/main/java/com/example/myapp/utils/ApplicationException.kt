package com.example.myapp.utils

sealed class ApplicationException(override val message: String) : Exception(message) {

    data class ApiException(override val message: String = API_EXCEPTION)
        : ApplicationException(message)

    data class InternetException(override val message: String = INTERNET_CONNECTION_EXCEPTION)
        : ApplicationException(message)

    companion object {
        const val INTERNET_CONNECTION_EXCEPTION = "Возникли проблемы с интернет-соединением. Проверьте подключение и повторите попытку"
        const val API_EXCEPTION = "Упс, проблемы на сервере. Повторите попытку"
    }
}
