package dev.suresh.search.ui.di

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import dev.suresh.search.ui.SearchUiState
import dev.suresh.search.ui.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier,
    onCLick: (String) -> Unit)
{
    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()


    SearchScreenContent(
        modifier = modifier,
        uiState = uiState,
        query = query,
        onQueryChange = viewModel ::updateQuery,
        onCLick = onCLick
    )


}

@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    query: String,
    onQueryChange: (String) -> Unit,
    onCLick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search Movie") },
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->

        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    uiState.error.let { Text(it) }
                }
            }

            uiState.data != null -> {
                uiState.data.let { data ->

                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        items(
                            count = data.count(),
                            key = { data.get(it).id },
                            contentType = {data.get(it).id}
                        ) { index ->
                            val item = data.get(index)

                            if(item.imageUrl.isEmpty()){
                                Box(modifier = Modifier.fillMaxSize()
                                    .padding(horizontal = 12.dp).padding(top = 12.dp),
                                    contentAlignment = Alignment.Center)
                                {
                                    Text("No Image Available")

                                }
                            }
                            else{
                                SubcomposeAsyncImage(
                                    model = item.imageUrl,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = null,
                                    modifier = modifier
                                        .padding(horizontal = 12.dp)
                                        .padding(top = 12.dp)
                                        .fillMaxWidth()
                                        .height(400.dp)
                                        .clickable{
                                            onCLick(item.id)
                                        }
                                        .clip(RoundedCornerShape(12.dp)),
                                    loading = {
                                        Box(modifier = Modifier.fillMaxSize()
                                            .height(400.dp),
                                            contentAlignment = Alignment.Center)
                                        {
                                            CircularProgressIndicator()

                                        }
                                    }, error = {
                                        Box(modifier = Modifier.fillMaxSize()
                                            .height(400.dp),
                                            contentAlignment = Alignment.Center)
                                        {
                                            Text("Something went wrong.")

                                        }
                                    }
                                )

                            }

                        }
                    }

                }

            }
        }
    }
}




