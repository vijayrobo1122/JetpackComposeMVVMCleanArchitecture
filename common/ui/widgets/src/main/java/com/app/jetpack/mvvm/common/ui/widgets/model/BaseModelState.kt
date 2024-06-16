package com.app.jetpack.mvvm.common.ui.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class BaseModelState(
    override val id: String = "",
    val page: Int = 0,
    val totalPages: Int = 0,
    val totalResults: Int = 0,
    val moviesList: List<MovieItemState> = emptyList(),
) : State {
    companion object {
        val EMPTY = BaseModelState()
    }
}
