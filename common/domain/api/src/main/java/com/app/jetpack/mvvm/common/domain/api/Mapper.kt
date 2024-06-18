package com.app.jetpack.mvvm.common.domain.api


interface Mapper<R, E> {
    fun mapTo(type: R?): E
}
