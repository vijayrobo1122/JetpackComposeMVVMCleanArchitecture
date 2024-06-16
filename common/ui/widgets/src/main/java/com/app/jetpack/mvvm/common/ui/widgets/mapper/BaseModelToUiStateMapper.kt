package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.common.domain.model.BaseModel
import com.app.jetpack.mvvm.common.ui.widgets.model.BaseModelState
import javax.inject.Inject

class BaseModelToUiStateMapper @Inject constructor(
    private val movieItemToUiStateMapper: MovieItemToUiStateMapper
) {

    fun map(param: BaseModel): BaseModelState {
        return BaseModelState(
            id = System.currentTimeMillis().toString(),
            page = param.page,
            totalPages = param.totalPages,
            totalResults = param.totalResults,
            moviesList = param.moviesList.map { movie -> movieItemToUiStateMapper.map(movie) },
        )
    }
}
