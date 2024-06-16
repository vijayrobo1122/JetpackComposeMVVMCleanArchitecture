package com.app.jetpack.mvvm.common.data.mapper.moviesdetail

import com.app.jetpack.mvvm.common.data.entity.moviedetail.SpokenLanguageEntity
import com.app.jetpack.mvvm.common.data.mapper.Mapper
import com.app.jetpack.mvvm.common.domain.model.moviedetail.SpokenLanguage
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
