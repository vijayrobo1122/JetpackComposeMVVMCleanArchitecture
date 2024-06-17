package com.app.jetpack.mvvm.common.data.mapper

interface Mapper<R, E> {
    fun mapTo(type: R?): E
}
