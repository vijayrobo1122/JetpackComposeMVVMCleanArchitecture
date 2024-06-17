package com.app.jetpack.mvvm.features.moviedetail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.common.domain.api.pagingLoadingState
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.ui.compositions.ArtistAndCrewCard
import com.app.jetpack.mvvm.common.ui.compositions.CircularIndeterminateProgressBar
import com.app.jetpack.mvvm.common.ui.compositions.MovieDetailCard
import com.app.jetpack.mvvm.common.ui.compositions.RecommendedMovieCard
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor
import com.app.jetpack.mvvm.common.ui.widgets.model.ArtistState
import com.app.jetpack.mvvm.common.ui.widgets.model.BaseModelState
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieDetailState

@Composable
fun MovieDetailScreen(
    onMovieItemClick: (String) -> Unit,
    onArtistItemClick: (String) -> Unit,
    movieId: Int,
) {
    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val progressBar = remember { mutableStateOf(false) }
    val movieDetail = movieDetailViewModel.movieDetail
    val recommendedMovie = movieDetailViewModel.recommendedMovie
    val artist = movieDetailViewModel.artist

    LaunchedEffect(true) {
        movieDetailViewModel.movieDetailApi(movieId)
        movieDetailViewModel.recommendedMovieApi(movieId, 1)
        movieDetailViewModel.movieCredit(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DefaultBackgroundColor
            )
    ) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
        movieDetail.value?.let { it ->
            if (it is DataState.Success<MovieDetailState>) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                    MovieDetailCard(
                        movieDetailState = it.data
                    )

                    recommendedMovie.value?.let {
                        if (it is DataState.Success<BaseModelState>) {
                            RecommendedMovieCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 10.dp, end = 10.dp),
                                recommendedMovie = it.data.moviesList,
                                onItemClick = onMovieItemClick,
                            )
                        }
                    }

                    artist.value?.let {
                        if (it is DataState.Success<ArtistState>) {
                            ArtistAndCrewCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 10.dp, end = 10.dp),
                                castStates = it.data.castList,
                                onArtistItemClick = onArtistItemClick,
                            )
                        }
                    }
                }
            }
        }
        recommendedMovie.pagingLoadingState {
            progressBar.value = it
        }
        movieDetail.pagingLoadingState {
            progressBar.value = it
        }
    }
}
