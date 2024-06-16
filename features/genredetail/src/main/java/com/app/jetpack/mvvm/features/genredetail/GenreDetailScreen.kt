package com.app.jetpack.mvvm.features.genredetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.common.ui.compositions.MoviesListWidget

@Composable
fun GenreDetailScreen(
    isShowExitAppDialog: Boolean,
    onMovieItemClick: (String) -> Unit,
    genreId: String
) {
    val genreViewModel = hiltViewModel<GenreDetailViewModel>()
    MoviesListWidget(
        isShowExitAppDialog = isShowExitAppDialog,
        movies = genreViewModel.moviesByGenre(genreId),
        null,
        null,
        onMovieItemClick = onMovieItemClick
    ) {

    }
}
