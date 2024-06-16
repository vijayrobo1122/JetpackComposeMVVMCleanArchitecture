package com.app.jetpack.mvvm.features.moviedetail

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources

fun NavController.navigateToMovieDetailScreen(movieId: String) {
    this.navigate(Screen.MovieDetail.route.plus("/$movieId"))
}

fun NavGraphBuilder.movieDetailsMovieScreen(
    onMovieItemClick: (String) -> Unit,
    onArtistItemClick: (String) -> Unit,
) {
    composable(
        Screen.MovieDetail.route.plus(Screen.MovieDetail.objectPath),
        arguments = listOf(navArgument(Screen.MovieDetail.objectName) {
            type = NavType.IntType
        })
    ) {
        label = stringResource(StringResources.titleMovieDetail)
        val movieId = it.arguments?.getInt(Screen.MovieDetail.objectName)
        movieId?.let {
            MovieDetailScreen(
                onMovieItemClick = onMovieItemClick,
                onArtistItemClick = onArtistItemClick,
                movieId = movieId,
            )
        }
    }
}
