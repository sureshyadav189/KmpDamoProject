package dev.suresh.details.domen.repository

import dev.suresh.details.domen.model.MovieDetails

interface DetailsRepository {

    suspend fun getMovieDetails(id: String): Result<MovieDetails>

}

