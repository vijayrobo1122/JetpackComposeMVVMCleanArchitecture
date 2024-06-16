package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.common.domain.model.artist.Cast
import com.app.jetpack.mvvm.common.ui.widgets.model.CastState
import javax.inject.Inject

class CastToUiStateMapper @Inject constructor() {

    fun map(param: Cast): CastState {
        return CastState(
            id = param.id.toString(),
            castId = param.id,
            isAdult = param.isAdult,
            character = param.character,
            creditId = param.creditId,
            gender = param.gender,
            knownForDepartment = param.knownForDepartment,
            name = param.name,
            order = param.order,
            originalName = param.originalName,
            popularity = param.popularity,
            profilePath = param.profilePath,
        )
    }
}
