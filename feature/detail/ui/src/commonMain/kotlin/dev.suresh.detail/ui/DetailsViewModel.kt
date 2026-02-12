package dev.suresh.detail.ui

import androidx.lifecycle.viewModelScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import dev.suresh.details.domen.usecase.GetMovieDetailsUsecase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(private val detailsUsecase: GetMovieDetailsUsecase) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope,DetailsUiState())

    @NativeCoroutines
    val uiState : StateFlow<DetailsUiState> = _uiState

    fun getMovie(id: String) = viewModelScope.launch{
        _uiState.update { DetailsUiState(isLoading = true) }
        detailsUsecase.execute(id).onSuccess {data ->
            _uiState.update { DetailsUiState(movieDetails = data)}
            }.onFailure {error ->
            _uiState.update { DetailsUiState(isError = error.message ?: "")}
        }

    }
}