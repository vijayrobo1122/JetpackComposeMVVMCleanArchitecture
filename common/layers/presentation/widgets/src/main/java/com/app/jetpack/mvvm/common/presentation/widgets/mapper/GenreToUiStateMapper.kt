package com.app.jetpack.mvvm.common.presentation.widgets.mapper


import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState
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
