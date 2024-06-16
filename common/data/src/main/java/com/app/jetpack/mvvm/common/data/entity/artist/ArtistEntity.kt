package com.app.jetpack.mvvm.common.data.entity.artist

import com.google.gson.annotations.SerializedName

data class ArtistEntity(
    @SerializedName("cast")
    val cast: List<CastEntity>,
    @SerializedName("crew")
    val crew: List<CrewEntity>,
    @SerializedName("id")
    val id: Int
)
