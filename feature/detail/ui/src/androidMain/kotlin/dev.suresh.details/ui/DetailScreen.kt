package dev.suresh.details.ui

import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import dev.suresh.detail.ui.DetailsUiState
import dev.suresh.detail.ui.DetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailsScreen(modifier: Modifier,movieId : String){
    val detailsViewmodel = koinViewModel<DetailsViewModel>()
    val uiState by detailsViewmodel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(movieId) {
        detailsViewmodel.getMovie(movieId)
    }

    DetailsScreenContent(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun DetailsScreenContent(
    modifier: Modifier,
    uiState: DetailsUiState)
{

    Scaffold{innerPadding ->
        when{
            uiState.isLoading -> {
                Box(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            uiState.isError.isEmpty() -> {

                Box(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(uiState.isError)
                }
            }
            uiState.movieDetails !=null -> {

                val movieDetails = uiState.movieDetails

                Column(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {
                    SubcomposeAsyncImage(
                        model = movieDetails.imageUrl,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 12.dp)
                            .height(600.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        loading = {
                            Box(modifier = Modifier.fillMaxSize()
                                .height(600.dp),
                                contentAlignment = Alignment.Center)
                            {
                                CircularProgressIndicator()

                            }
                        }, error = {
                            Box(modifier = Modifier.fillMaxSize()
                                .height(400.dp),
                                contentAlignment = Alignment.Center)
                            {
                                Text("Image Not Available.")

                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = movieDetails.title,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start

                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = movieDetails.overView,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start

                    )

                    Spacer(modifier = Modifier.height(64.dp))
                }

            }

        }
    }
}