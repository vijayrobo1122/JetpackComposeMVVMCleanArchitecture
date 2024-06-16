package com.app.jetpack.mvvm.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.jetpack.mvvm.common.navigation.Screen
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState
import com.app.jetpack.mvvm.features.artistdetail.artistDetailsScreen
import com.app.jetpack.mvvm.features.artistdetail.navigateToArtistDetailScreen
import com.app.jetpack.mvvm.features.genredetail.genreDetailScreen
import com.app.jetpack.mvvm.features.moviedetail.movieDetailsMovieScreen
import com.app.jetpack.mvvm.features.moviedetail.navigateToMovieDetailScreen
import com.app.jetpack.mvvm.features.nowplaying.nowPlayingMovieScreen
import com.app.jetpack.mvvm.features.popular.popularMovieScreen
import com.app.jetpack.mvvm.features.toprated.topRatedMovieScreen
import com.app.jetpack.mvvm.features.upcoming.upcomingMovieScreen

@Composable
fun AppNavigation(
    modifier: Modifier,
    navController: NavHostController,
    genresStateList: ArrayList<GenreState>? = null,
) {
    NavHost(navController, startDestination = Screen.Home.route, modifier) {
        nowPlayingMovieScreen(
            onMovieItemClick = navController::navigateToMovieDetailScreen,
            genresStateList = genresStateList,
        )
        popularMovieScreen(
            onMovieItemClick = navController::navigateToMovieDetailScreen,
            genresStateList = genresStateList,
        )
        topRatedMovieScreen(
            onMovieItemClick = navController::navigateToMovieDetailScreen,
            genresStateList = genresStateList,
        )
        upcomingMovieScreen(
            onMovieItemClick = navController::navigateToMovieDetailScreen,
            genresStateList = genresStateList,
        )
        movieDetailsMovieScreen(
            onMovieItemClick = navController::navigateToMovieDetailScreen,
            onArtistItemClick = navController::navigateToArtistDetailScreen,
        )
        artistDetailsScreen()
        genreDetailScreen(
            onMovieItemClick = navController::navigateToMovieDetailScreen,
        )
    }
}
