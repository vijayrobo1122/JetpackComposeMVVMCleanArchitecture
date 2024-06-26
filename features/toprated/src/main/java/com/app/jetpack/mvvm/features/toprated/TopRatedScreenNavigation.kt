package com.app.jetpack.mvvm.features.toprated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen

fun NavGraphBuilder.topRatedMovieScreen(
    onMovieItemClick: (String) -> Unit,
) {
    composable(route = Screen.TopRated.route) {
        TopRatedScreen(
            isShowExitAppDialog = false,
            onMovieItemClick = onMovieItemClick,
        )
    }
}
