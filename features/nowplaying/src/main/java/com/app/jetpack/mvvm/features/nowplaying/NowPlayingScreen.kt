package com.app.jetpack.mvvm.features.nowplaying

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.common.ui.compositions.MoviesListWidget

@Composable
fun NowPlayingScreen(
    isShowExitAppDialog: Boolean,
    onMovieItemClick: (String) -> Unit,
) {
    val viewModel = hiltViewModel<NowPlayingViewModel>()
    val genreStateList = viewModel.genreStateList.collectAsState()

    MoviesListWidget(
        isShowExitAppDialog = isShowExitAppDialog,
        movies = viewModel.nowPlayingMovies,
        genresStateList = genreStateList.value,
        selectedGenreState = viewModel.selectedGenre.value,
        onMovieItemClick = onMovieItemClick,
    ) {
        viewModel.genreIdData.value = it?.id ?: ""
        it?.let {
            viewModel.selectedGenre.value = it
        }
    }
}
