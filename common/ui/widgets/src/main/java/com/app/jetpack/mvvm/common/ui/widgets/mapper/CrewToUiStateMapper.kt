package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.business.artistdetail.domain.model.Crew
import com.app.jetpack.mvvm.common.ui.widgets.model.CrewState
import javax.inject.Inject

class CrewToUiStateMapper @Inject constructor() {

    fun map(param: Crew): CrewState {
        return CrewState(
            id = param.id.toString(),
            crewId = param.id,
            gender = param.gender,
            creditId = param.creditId,
            department = param.department,
            job = param.job,
            knownForDepartment = param.knownForDepartment,
            name = param.name,
            originalName = param.originalName,
            popularity = param.popularity,
            profilePath = param.profilePath,
            isAdult = param.isAdult,
        )
    }
}
