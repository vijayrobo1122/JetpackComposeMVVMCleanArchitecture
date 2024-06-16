package com.app.jetpack.mvvm.common.data.entity

import com.google.gson.annotations.SerializedName

data class BaseModelEntity(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieItemEntity>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
