package dev.suresh.search.domen.respository
import dev.suresh.coreNetwork.services.MovieApiService
import dev.suresh.search.domen.model.Movie
import dev.suresh.search.domen.repository.SearchRepository

class SearchRepositoryImp(private val movieApiService: MovieApiService) : SearchRepository {

    override suspend fun search(q: String): Result<List<Movie>> {

        return movieApiService.searchMovies(q).map { list ->
            list.map { it ->
                Movie(
                    id = it.id.toString(),
                    imageUrl = buildImageUrl(it.poster_path ?: ""),
                    title = it.title
                )
            }
        }

    }

    private fun buildImageUrl(path: String) : String{

        return if(path.isEmpty()) "" else "https://api.themoviedb.org/3$path"

    }

}