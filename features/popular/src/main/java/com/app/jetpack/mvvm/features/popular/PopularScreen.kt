package com.app.jetpack.mvvm.features.popular


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.common.domain.model.GenreId
import com.app.jetpack.mvvm.common.domain.model.moviedetail.Genre
import com.app.jetpack.mvvm.common.ui.compositions.MoviesListWidget

@Composable
fun PopularScreen(
    isShowExitAppDialog: Boolean,
    onMovieItemClick: (String) -> Unit,
    genres: ArrayList<Genre>? = null,
) {
    val viewModel = hiltViewModel<PopularViewModel>()

    MoviesListWidget(
        isShowExitAppDialog = isShowExitAppDialog,
        movies = viewModel.popularMovies,
        genresStateList = genres?.map { viewModel.genreToUiStateMapper.map(it) },
        selectedGenreState = viewModel.selectedGenre.value,
        onMovieItemClick = onMovieItemClick
    ) {
        viewModel.filterData.value = GenreId(it?.id ?: "")
        it?.let {
            viewModel.selectedGenre.value = it
        }
    }
}
