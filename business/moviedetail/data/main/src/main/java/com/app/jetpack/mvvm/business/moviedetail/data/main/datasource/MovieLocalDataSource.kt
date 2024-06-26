package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource

import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.GenreEntity

interface MovieLocalDataSource {

    suspend fun likeMovie(movieId: Int)

    suspend fun unlikeMovie(movieId: Int)

    suspend fun isMovieLiked(movieId: Int): Boolean

    suspend fun getAllGenres(): List<GenreEntity>

    suspend fun insertAllGenres(data: List<GenreEntity>)
}
