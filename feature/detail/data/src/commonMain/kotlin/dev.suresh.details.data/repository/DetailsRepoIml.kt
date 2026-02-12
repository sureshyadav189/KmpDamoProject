package dev.suresh.details.data.repository

import dev.suresh.coreNetwork.services.MovieApiService
import dev.suresh.details.domen.model.MovieDetails
import dev.suresh.details.domen.repository.DetailsRepository

class DetailsRepoIml(private val apiService: MovieApiService) : DetailsRepository {

    override suspend fun getMovieDetails(id: String): Result<MovieDetails>   {
        return apiService.getMovieDetails(id).map { data ->
            MovieDetails(
                id = data.id.toString(),
                title = data.title,
                overView = data.overview,
                imageUrl = buildImageUrl(data.poster_path ?: "")
            )
        }

    }

    private fun buildImageUrl(path: String) : String{

        return if(path.isEmpty()) "" else "https://api.themoviedb.org/3$path"

    }
}