package dev.suresh.detail.ui

import dev.suresh.details.domen.model.MovieDetails

data class DetailsUiState(
    val isLoading : Boolean = false,
    val isError : String = "",
    val movieDetails: MovieDetails? = null,
)
