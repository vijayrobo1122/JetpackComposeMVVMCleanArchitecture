package com.app.jetpack.mvvm.common.presentation.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class CastState(
    override val id: String = "",
    val castId: Int = 0,
    val gender: Int = 0,
    val order: Int = 0,
    val character: String = "",
    val creditId: String = "",
    val knownForDepartment: String = "",
    val name: String = "",
    val originalName: String = "",
    val profilePath: String = "",
    val popularity: Double = 0.0,
    val isAdult: Boolean = false,
) : State {
    companion object {
        val EMPTY = CastState()
    }
}
