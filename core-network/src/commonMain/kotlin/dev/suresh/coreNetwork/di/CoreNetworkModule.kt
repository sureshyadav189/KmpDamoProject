package dev.suresh.coreNetwork.di

import dev.suresh.coreNetwork.client.HttpClientFactory
import dev.suresh.coreNetwork.services.MovieApiService
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreNetworkModule() : Module {
    return module {
        single {
            MovieApiService(HttpClientFactory.getInstance())
        }
    }
}