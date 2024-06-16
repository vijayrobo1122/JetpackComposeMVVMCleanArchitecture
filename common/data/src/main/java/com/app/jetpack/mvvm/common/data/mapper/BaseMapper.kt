package com.app.jetpack.mvvm.common.data.mapper

import com.app.jetpack.mvvm.common.domain.DataState

interface Mapper<R, E> {
    fun mapTo(type: R?): E
}

fun <R, E> mapToResponse(result: DataState<R>, mapper: Mapper<R, E>): DataState<E> {
    return when (result) {
        is DataState.Success -> DataState.Success(mapper.mapTo(result.data))
        is DataState.Error -> DataState.Error(result.exception)
        is DataState.Loading -> DataState.Loading
    }
}
