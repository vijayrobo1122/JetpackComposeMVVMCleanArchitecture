package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local

import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.FavoriteEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.GenreEntity
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.MovieLocalDataSource
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.FavoriteDao
import com.app.jetpack.mvvm.business.moviedetail.data.main.datasource.local.dao.GenreDao
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val genreDao: GenreDao,
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

    override suspend fun getAllGenres(): List<GenreEntity> {
        return genreDao.getAllData()
    }

    override suspend fun insertAllGenres(data: List<GenreEntity>) {
        genreDao.insertAll(data)
    }
}
