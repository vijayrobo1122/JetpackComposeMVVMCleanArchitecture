package com.app.jetpack.mvvm.features.toprated

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.jetpack.mvvm.common.domain.model.moviedetail.Genre
import com.app.jetpack.mvvm.common.navigation.Screen

fun NavGraphBuilder.topRatedMovieScreen(
    onMovieItemClick: (String) -> Unit,
    genres: ArrayList<Genre>? = null,
) {
    composable(route = Screen.TopRated.route) {
        TopRatedScreen(
            isShowExitAppDialog = false,
            onMovieItemClick = onMovieItemClick,
            genres = genres
        )
    }
}
