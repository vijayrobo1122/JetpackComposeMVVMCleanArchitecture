package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.business.moviedetail.domain.model.SpokenLanguage
import com.app.jetpack.mvvm.common.ui.widgets.model.SpokenLanguageState
import javax.inject.Inject

class SpokenLanguageToUiStateMapper @Inject constructor() {

    fun map(param: SpokenLanguage): SpokenLanguageState {
        return SpokenLanguageState(
            id = param.isoName,
            englishName = param.englishName,
            isoName = param.isoName,
            name = param.name,
        )
    }
}
