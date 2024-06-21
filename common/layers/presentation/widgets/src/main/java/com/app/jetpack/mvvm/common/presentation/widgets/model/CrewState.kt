package com.app.jetpack.mvvm.common.presentation.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class CrewState(
    override val id: String = "",
    val crewId: Int = 0,
    val gender: Int = 0,
    val creditId: String = "",
    val department: String = "",
    val job: String = "",
    val knownForDepartment: String = "",
    val name: String = "",
    val originalName: String = "",
    val profilePath: String = "",
    val popularity: Double = 0.0,
    val isAdult: Boolean = false,
) : State {
    companion object {
        val EMPTY = CrewState()
    }
}
