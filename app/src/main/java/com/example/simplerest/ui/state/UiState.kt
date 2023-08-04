package com.example.simplerest.ui.state


sealed class UiState {
    data class Loading(val isLoading: Boolean) : UiState()
    data class Error(val exception: Throwable): UiState()
}