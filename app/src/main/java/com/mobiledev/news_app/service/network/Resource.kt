package com.mobiledev.news_app.service.network

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = true
) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Loading<T>(isLoading: Boolean = true) : Resource<T>(isLoading = isLoading)
    class Error<T>(message: String) : Resource<T>(message = message)
}