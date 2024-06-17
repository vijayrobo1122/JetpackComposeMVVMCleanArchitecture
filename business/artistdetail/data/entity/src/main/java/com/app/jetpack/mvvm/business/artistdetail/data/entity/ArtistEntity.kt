package com.app.jetpack.mvvm.business.artistdetail.data.entity

import com.google.gson.annotations.SerializedName

data class ArtistEntity(
    @SerializedName("cast")
    val cast: List<CastEntity>,
    @SerializedName("crew")
    val crew: List<CrewEntity>,
    @SerializedName("id")
    val id: Int
)
