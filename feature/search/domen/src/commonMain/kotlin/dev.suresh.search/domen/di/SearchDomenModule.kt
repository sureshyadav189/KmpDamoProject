package dev.suresh.search.domen.di

import dev.suresh.search.domen.usecases.SearchUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDomenModule() : Module{
    return module {
        factory {
            SearchUseCase(get())
        }

    }
}