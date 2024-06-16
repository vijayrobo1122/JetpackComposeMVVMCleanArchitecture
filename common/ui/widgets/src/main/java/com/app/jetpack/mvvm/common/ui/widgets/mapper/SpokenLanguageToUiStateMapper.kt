package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.common.domain.model.moviedetail.SpokenLanguage
import com.app.jetpack.mvvm.common.ui.widgets.model.SpokenLanguageState
import javax.inject.Inject

class SpokenLanguageToUiStateMapper @Inject constructor() {

    fun map(param: SpokenLanguage): SpokenLanguageState {
        return SpokenLanguageState(
            id = param.iso_639_1,
            englishName = param.englishName,
            iso_639_1 = param.iso_639_1,
            name = param.name,
        )
    }
}
