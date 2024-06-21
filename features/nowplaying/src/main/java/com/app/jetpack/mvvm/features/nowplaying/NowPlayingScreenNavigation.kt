package com.app.jetpack.mvvm.features.nowplaying

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState

fun NavGraphBuilder.nowPlayingMovieScreen(
    onMovieItemClick: (String) -> Unit,
    genresStateList: ArrayList<GenreState>? = null,
) {
    composable(route = Screen.Home.route) {
        NowPlayingScreen(
            isShowExitAppDialog = true,
            onMovieItemClick = onMovieItemClick,
            genresStateList = genresStateList,
        )
    }
}
