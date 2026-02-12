package dev.suresh.details.data.di

import dev.suresh.details.data.repository.DetailsRepoIml
import dev.suresh.details.domen.repository.DetailsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDataModule() : Module {
    return module {
        single<DetailsRepository> {
            DetailsRepoIml(get())
        }
    }
}