package com.app.jetpack.mvvm.common.domain.model

data class BaseModel(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val moviesList: List<MovieItem>,
)
