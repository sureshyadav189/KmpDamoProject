package dev.suresh.search.ui.di

import dev.suresh.search.ui.SearchViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

private val viewModelModule  = module{
factory { SearchViewModel(get()) }
}

actual fun getSearchUiModule(): org.koin.core.module.Module {
 return viewModelModule
}

class SearchViewmodelProvider : KoinComponent{

    fun serarchViewModel() : SearchViewModel = get()
}