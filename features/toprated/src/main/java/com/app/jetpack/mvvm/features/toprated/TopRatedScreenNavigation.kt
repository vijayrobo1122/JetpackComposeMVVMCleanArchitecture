package com.app.jetpack.mvvm.features.toprated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState

fun NavGraphBuilder.topRatedMovieScreen(
    onMovieItemClick: (String) -> Unit,
    genresStateList: ArrayList<GenreState>? = null,
) {
    composable(route = Screen.TopRated.route) {
        TopRatedScreen(
            isShowExitAppDialog = false,
            onMovieItemClick = onMovieItemClick,
            genresStateList = genresStateList
        )
    }
}
