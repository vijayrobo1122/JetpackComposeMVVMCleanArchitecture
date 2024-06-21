package com.app.jetpack.mvvm.common.presentation.widgets.mapper

import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.common.presentation.widgets.model.BaseModelState
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
