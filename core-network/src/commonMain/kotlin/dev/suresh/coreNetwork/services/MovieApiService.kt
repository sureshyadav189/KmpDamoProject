package dev.suresh.coreNetwork.services

import dev.suresh.coreNetwork.model.MovieDTO
import dev.suresh.coreNetwork.model.MovieDetailsResponse
import dev.suresh.coreNetwork.model.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters

class MovieApiService(private val httpClient: HttpClient) {

    suspend fun searchMovies(query: String): Result<List<MovieDTO>> {
        return try {
            val searchResponse = httpClient.get("3/search/movie") {
                parameter("query", query)

            }.body<SearchResponse>()
             Result.success(searchResponse.results)
        }
        catch (e: Exception){
            Result.failure(e)
        }

    }

    //https://api.themoviedb.org/3/movie/{movie_id}

    suspend fun getMovieDetails (id: String) : Result<MovieDetailsResponse>{
        return try {
         val movieDetailsResponse =   httpClient.get("3/review/${id}")
             Result.success(movieDetailsResponse.body())
        }
        catch (e: Exception){
            Result.failure(e)
        }

    }

}