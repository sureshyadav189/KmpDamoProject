package dev.suresh.details.domen.di

import dev.suresh.details.domen.usecase.GetMovieDetailsUsecase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDomenModule () : Module {

    return module {
        factory { GetMovieDetailsUsecase(get()) }

    }
}