package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.SpokenLanguageEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.SpokenLanguage
import com.app.jetpack.mvvm.common.domain.api.Mapper
import javax.inject.Inject

class SpokenLanguageMapper @Inject constructor() :
    Mapper<SpokenLanguageEntity, SpokenLanguage> {

    override fun mapTo(type: SpokenLanguageEntity?): SpokenLanguage {
        return SpokenLanguage(
            englishName = type?.english_name.orEmpty(),
            iso_639_1 = type?.iso_639_1.orEmpty(),
            name = type?.name.orEmpty(),
        )
    }
}
