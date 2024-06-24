package com.app.jetpack.mvvm.features.moviedetail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.common.ui.compositions.ArtistAndCrewCard
import com.app.jetpack.mvvm.common.ui.compositions.CircularIndeterminateProgressBar
import com.app.jetpack.mvvm.common.ui.compositions.MovieDetailCard
import com.app.jetpack.mvvm.common.ui.compositions.RecommendedMovieCard
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor

@SuppressWarnings("LongMethod")
@Composable
fun MovieDetailScreen(
    onMovieItemClick: (String) -> Unit,
    onArtistItemClick: (String) -> Unit,
    movieId: Int,
) {
    val viewModel = hiltViewModel<MovieDetailViewModel>()
    val progressBar = remember { mutableStateOf(false) }
    val movieDetail = viewModel.movieDetail.collectAsState()
    val recommendedMovie = viewModel.recommendedMovie.collectAsState()
    val artist = viewModel.artist.collectAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(true) {
        viewModel.movieDetailApi(movieId)
        viewModel.recommendedMovieApi(movieId, 1)
        viewModel.movieCredit(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DefaultBackgroundColor
            )
    ) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value)
        movieDetail.value?.let { it ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                MovieDetailCard(
                    movieDetailState = it,
                    toggleFavorite = { isFavorite ->
                        viewModel.toggleFavorite(it.movieId, isFavorite)
                    }
                )

                recommendedMovie.value?.let {
                    RecommendedMovieCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp),
                        recommendedMovie = it.moviesList,
                        onItemClick = onMovieItemClick,
                    )
                }

                artist.value?.let {
                    ArtistAndCrewCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp),
                        castStates = it.castList,
                        onArtistItemClick = onArtistItemClick,
                    )
                }
            }
        }
        progressBar.value = isLoading
    }
}
