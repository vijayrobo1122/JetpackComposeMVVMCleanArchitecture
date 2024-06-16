package com.app.jetpack.mvvm.common.domain.model.artist

data class Artist(
    val id: Int,
    val castList: List<Cast>,
    val crewList: List<Crew>,
)
