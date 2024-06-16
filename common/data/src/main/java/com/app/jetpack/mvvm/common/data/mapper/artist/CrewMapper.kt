package com.app.jetpack.mvvm.common.data.mapper.artist

import com.app.jetpack.mvvm.common.data.entity.artist.CrewEntity
import com.app.jetpack.mvvm.common.data.mapper.Mapper
import com.app.jetpack.mvvm.common.domain.model.artist.Crew
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
