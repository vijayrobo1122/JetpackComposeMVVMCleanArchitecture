package com.app.jetpack.mvvm.features.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.GenreId
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState
import com.app.jetpack.mvvm.common.ui.compositions.MoviesListWidget

@Composable
fun NowPlayingScreen(
    isShowExitAppDialog: Boolean,
    onMovieItemClick: (String) -> Unit,
    genresStateList: ArrayList<GenreState>? = null,
) {
    val viewModel = hiltViewModel<NowPlayingViewModel>()

    MoviesListWidget(
        isShowExitAppDialog = isShowExitAppDialog,
        movies = viewModel.nowPlayingMovies,
        genresStateList = genresStateList,
        selectedGenreState = viewModel.selectedGenre.value,
        onMovieItemClick = onMovieItemClick,
    ) {
        viewModel.filterData.value = GenreId(it?.id ?: "")
        it?.let {
            viewModel.selectedGenre.value = it
        }
    }
}
