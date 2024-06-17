package com.app.jetpack.mvvm.business.artistdetail.domain.model

data class Artist(
    val id: Int,
    val castList: List<Cast>,
    val crewList: List<Crew>,
)
