package com.app.jetpack.mvvm.features.upcoming


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState
import com.app.jetpack.mvvm.common.ui.compositions.MoviesListWidget


@Composable
fun UpcomingScreen(
    isShowExitAppDialog: Boolean,
    onMovieItemClick: (String) -> Unit,
) {
    val viewModel = hiltViewModel<UpComingViewModel>()
    val genreStateList: List<GenreState> by viewModel.genreStateList.collectAsState(emptyList())

    MoviesListWidget(
        isShowExitAppDialog = isShowExitAppDialog,
        movies = viewModel.upcomingMovies,
        genresStateList = genreStateList,
        selectedGenreState = viewModel.selectedGenre.value,
        onMovieItemClick = onMovieItemClick
    ) {
        viewModel.genreIdData.value = it?.id ?: ""
        it?.let {
            viewModel.selectedGenre.value = it
        }
    }
}
