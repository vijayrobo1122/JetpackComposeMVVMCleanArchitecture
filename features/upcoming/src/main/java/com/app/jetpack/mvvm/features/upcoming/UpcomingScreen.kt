package com.app.jetpack.mvvm.features.upcoming


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.GenreId
import com.app.jetpack.mvvm.common.ui.compositions.MoviesListWidget
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState


@Composable
fun UpcomingScreen(
    isShowExitAppDialog: Boolean,
    onMovieItemClick: (String) -> Unit,
    genresStateList: ArrayList<GenreState>? = null,
) {
    val viewModel = hiltViewModel<UpComingViewModel>()
    MoviesListWidget(
        isShowExitAppDialog = isShowExitAppDialog,
        movies = viewModel.upcomingMovies,
        genresStateList = genresStateList,
        selectedGenreState = viewModel.selectedGenre.value,
        onMovieItemClick = onMovieItemClick
    ) {
        viewModel.filterData.value = GenreId(it?.id ?: "")
        it?.let {
            viewModel.selectedGenre.value = it
        }
    }
}
