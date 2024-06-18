package com.app.jetpack.mvvm.business.moviedetail.data.entity

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionEntity(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)
