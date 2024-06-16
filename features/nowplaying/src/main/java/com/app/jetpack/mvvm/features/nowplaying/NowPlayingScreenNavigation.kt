package com.app.jetpack.mvvm.features.nowplaying

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.domain.model.moviedetail.Genre
import com.app.jetpack.mvvm.common.navigation.Screen

fun NavGraphBuilder.nowPlayingMovieScreen(
    onMovieItemClick: (String) -> Unit,
    genres: ArrayList<Genre>? = null,
) {
    composable(route = Screen.Home.route) {
        NowPlayingScreen(
            isShowExitAppDialog = true,
            onMovieItemClick = onMovieItemClick,
            genres = genres,
        )
    }
}
