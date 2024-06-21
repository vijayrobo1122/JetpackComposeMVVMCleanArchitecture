package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local

import com.app.jetpack.mvvm.business.moviedetail.data.entity.FavoriteEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieLocalDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.FavoriteDao
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
) : MovieLocalDataSource {

    override suspend fun likeMovie(movieId: Int) {
        favoriteDao.insertFavorite(FavoriteEntity(movieId = movieId))
    }

    override suspend fun unlikeMovie(movieId: Int) {
        favoriteDao.deleteFavorite(movieId)
    }

    override suspend fun isMovieLiked(movieId: Int): Boolean {
        return favoriteDao.isFavorite(movieId) > 0
    }
}
