package dev.suresh.details.domen.usecase

import dev.suresh.details.domen.model.MovieDetails
import dev.suresh.details.domen.repository.DetailsRepository

class GetMovieDetailsUsecase (private val detailsRepository: DetailsRepository) {

    suspend fun execute(id: String) = detailsRepository.getMovieDetails(id)

}