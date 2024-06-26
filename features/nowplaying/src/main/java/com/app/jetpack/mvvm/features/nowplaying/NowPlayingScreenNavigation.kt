package com.app.jetpack.mvvm.features.nowplaying

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen

fun NavGraphBuilder.nowPlayingMovieScreen(
    onMovieItemClick: (String) -> Unit,
) {
    composable(route = Screen.Home.route) {
        NowPlayingScreen(
            isShowExitAppDialog = true,
            onMovieItemClick = onMovieItemClick,
        )
    }
}
