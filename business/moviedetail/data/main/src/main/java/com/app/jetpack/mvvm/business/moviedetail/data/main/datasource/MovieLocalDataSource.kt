package com.app.jetpack.mvvm.business.moviedetail.data.main.datasource

interface MovieLocalDataSource {

    suspend fun likeMovie(movieId: Int)

    suspend fun unlikeMovie(movieId: Int)

    suspend fun isMovieLiked(movieId: Int): Boolean
}
