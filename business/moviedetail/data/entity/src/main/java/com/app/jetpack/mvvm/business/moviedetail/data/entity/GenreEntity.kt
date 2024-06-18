package com.app.jetpack.mvvm.business.moviedetail.data.entity

import com.google.gson.annotations.SerializedName

data class GenreEntity(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String
)
