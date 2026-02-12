package dev.suresh.search.domen.di

import dev.suresh.search.domen.repository.SearchRepository
import dev.suresh.search.domen.respository.SearchRepositoryImp
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDataModule() : Module{
    return module {
        single<SearchRepository> {
            SearchRepositoryImp(get())
        }
    }
}