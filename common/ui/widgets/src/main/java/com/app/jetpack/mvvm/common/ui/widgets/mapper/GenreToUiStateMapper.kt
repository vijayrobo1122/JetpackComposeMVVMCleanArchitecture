package com.app.jetpack.mvvm.common.ui.widgets.mapper


import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState
import javax.inject.Inject

class GenreToUiStateMapper @Inject constructor() {

    fun map(param: Genre): GenreState {
        return GenreState(
            id = param.id.toString(),
            genreId = param.id,
            name = param.name,
        )
    }
}
