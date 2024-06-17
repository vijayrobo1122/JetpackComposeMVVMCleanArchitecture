package com.app.jetpack.mvvm.business.artistdetail.data.main.mapper

import com.app.jetpack.mvvm.business.artistdetail.data.entity.CrewEntity
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Crew
import com.app.jetpack.mvvm.common.domain.api.Mapper
import com.app.jetpack.mvvm.common.general.extensions.orFalse
import javax.inject.Inject

class CrewMapper @Inject constructor() : Mapper<CrewEntity, Crew> {

    override fun mapTo(type: CrewEntity?): Crew {
        return Crew(
            isAdult = type?.adult.orFalse(),
            creditId = type?.creditId.orEmpty(),
            department = type?.department.orEmpty(),
            gender = type?.gender ?: 0,
            id = type?.id ?: 0,
            job = type?.job.orEmpty(),
            knownForDepartment = type?.knownForDepartment.orEmpty(),
            name = type?.name.orEmpty(),
            originalName = type?.originalName.orEmpty(),
            popularity = type?.popularity ?: 0.0,
            profilePath = type?.profilePath.orEmpty(),
        )
    }
}
