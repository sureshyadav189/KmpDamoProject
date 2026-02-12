package com.example.kmpdamoproject.di

import dev.suresh.coreNetwork.di.getCoreNetworkModule
import dev.suresh.detail.di.getDetailsUiModule
import dev.suresh.details.data.di.getDetailsDataModule
import dev.suresh.details.domen.di.getDetailsDomenModule
import dev.suresh.search.domen.di.getSearchDataModule
import dev.suresh.search.domen.di.getSearchDomenModule
import dev.suresh.search.ui.di.getSearchUiModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(
            getCoreNetworkModule(),

            getSearchUiModule(),
            getSearchDataModule(),
            getSearchDomenModule(),

            getDetailsUiModule(),
            getDetailsDataModule(),
            getDetailsDomenModule()

        )
    }
}