package dev.suresh.search.ui

import dev.suresh.search.domen.model.Movie

data class SearchUiState(
    val isLoading: Boolean = false,
    val data: List<Movie>? =null,
    val error: String = ""
)
