package dev.suresh.detail.di

import androidx.compose.ui.text.rememberTextMeasurer
import dev.suresh.detail.ui.DetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


actual fun getDetailsUiModule(): Module {
    return module {
        viewModel { DetailsViewModel(get()) }
    }
}