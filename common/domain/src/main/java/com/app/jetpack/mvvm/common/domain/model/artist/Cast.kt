package com.app.jetpack.mvvm.common.domain.model.artist

data class Cast(
    val id: Int,
    val castId: Int,
    val gender: Int,
    val order: Int,
    val character: String,
    val creditId: String,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val profilePath: String,
    val popularity: Double,
    val isAdult: Boolean,
)
