package com.app.jetpack.mvvm.common.domain.models

sealed class DataState<out R> {
    data object Loading : DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error<out T>(val exception: Exception) : DataState<T>()
}
