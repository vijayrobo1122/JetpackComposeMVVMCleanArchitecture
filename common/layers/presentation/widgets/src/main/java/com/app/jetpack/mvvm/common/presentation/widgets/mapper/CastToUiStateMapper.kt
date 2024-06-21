package com.app.jetpack.mvvm.common.presentation.widgets.mapper

import com.app.jetpack.mvvm.business.artistdetail.domain.model.Cast
import com.app.jetpack.mvvm.common.presentation.widgets.model.CastState
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
