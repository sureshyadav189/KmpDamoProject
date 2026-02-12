package dev.suresh.search.domen.repository

import dev.suresh.search.domen.model.Movie

interface SearchRepository {

    suspend fun search(q: String) : Result<List<Movie>>
}