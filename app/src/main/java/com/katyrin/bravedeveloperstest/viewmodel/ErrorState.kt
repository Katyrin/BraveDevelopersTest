package com.katyrin.bravedeveloperstest.viewmodel

sealed class ErrorState {
    object NotFound : ErrorState()
    object TimOut : ErrorState()
    object UnknownHost : ErrorState()
    object Connection : ErrorState()
    object Server : ErrorState()
    data class Unknown(val message: String?) : ErrorState()
}