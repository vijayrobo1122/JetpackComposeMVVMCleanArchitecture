package com.app.jetpack.mvvm.common.ui.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class GenreState(
    override val id: String = "",
    val genreId: Int? = null,
    val name: String = "",
) : State {
    companion object {
        val EMPTY = GenreState()
    }
}
