package com.katyrin.bravedeveloperstest.viewmodel

sealed class AppState<out T> {
    data class Success<out T>(val value: T) : AppState<T>()
    data class Error(val errorState: ErrorState) : AppState<Nothing>()
    object Loading : AppState<Nothing>()
}