package com.app.jetpack.mvvm.common.domain.model.artist

data class Crew(
    val id: Int,
    val gender: Int,
    val creditId: String,
    val department: String,
    val job: String,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val profilePath: String,
    val popularity: Double,
    val isAdult: Boolean,
)
