package dev.suresh.search.domen.usecases

import dev.suresh.search.domen.repository.SearchRepository

class SearchUseCase(private val searchRepository: SearchRepository) {

    suspend fun execute(q: String) = searchRepository.search(q)

}