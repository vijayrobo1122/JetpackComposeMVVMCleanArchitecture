package com.app.jetpack.mvvm.business.artistdetail.data.main.mapper

import com.app.jetpack.mvvm.business.artistdetail.data.entity.CastEntity
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Cast
import com.app.jetpack.mvvm.common.domain.api.Mapper
import com.app.jetpack.mvvm.common.general.extensions.orFalse
import javax.inject.Inject

class CastMapper @Inject constructor() : Mapper<CastEntity, Cast> {

    override fun mapTo(type: CastEntity?): Cast {
        return Cast(
            isAdult = type?.adult.orFalse(),
            castId = type?.castId ?: 0,
            character = type?.character.orEmpty(),
            creditId = type?.creditId.orEmpty(),
            gender = type?.gender ?: 0,
            id = type?.id ?: 0,
            knownForDepartment = type?.knownForDepartment.orEmpty(),
            name = type?.name.orEmpty(),
            order = type?.order ?: 0,
            originalName = type?.originalName.orEmpty(),
            popularity = type?.popularity ?: 0.0,
            profilePath = type?.profilePath.orEmpty(),
        )
    }
}
