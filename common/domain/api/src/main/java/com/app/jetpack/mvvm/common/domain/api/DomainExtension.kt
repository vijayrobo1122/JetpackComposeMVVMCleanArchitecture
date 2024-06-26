package com.app.jetpack.mvvm.common.domain.api

import androidx.compose.runtime.MutableState
import com.app.jetpack.mvvm.common.domain.models.DataState

fun <T : Any> MutableState<DataState<T>?>.pagingLoadingState(isLoaded: (pagingState: Boolean) -> Unit) {
    when (this.value) {
        is DataState.Success<T> -> {
            isLoaded(false)
        }

        is DataState.Loading -> {
            isLoaded(true)
        }

        is DataState.Error -> {
            isLoaded(false)
        }

        else -> {
            //DO_NOTHING
        }
    }
}
