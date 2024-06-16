package com.app.jetpack.mvvm.common.ui.widgets.model

import com.app.jetpack.mvvm.common.general.models.State

data class BelongsToCollectionState(
    override val id: String = "",
    val belogsToCollectionId: Int = 0,
    val backdropPath: String = "",
    val name: String = "",
    val posterPath: String = "",
) : State {
    companion object {
        val EMPTY = BelongsToCollectionState()
    }
}
