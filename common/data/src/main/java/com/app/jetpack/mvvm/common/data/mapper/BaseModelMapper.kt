package com.app.jetpack.mvvm.common.data.mapper

import com.app.jetpack.mvvm.common.data.entity.BaseModelEntity
import com.app.jetpack.mvvm.common.domain.model.BaseModel
import javax.inject.Inject

class BaseModelMapper @Inject constructor(
    private val movieItemMapper: MovieItemMapper
) : Mapper<BaseModelEntity, BaseModel> {

    override fun mapTo(type: BaseModelEntity?): BaseModel {
        return BaseModel(
            page = type?.page ?: 0,
            moviesList = type?.results?.map { _movie -> movieItemMapper.mapTo(_movie) }
                ?: emptyList(),
            totalPages = type?.totalPages ?: 0,
            totalResults = type?.totalResults ?: 0,
        )
    }
}
