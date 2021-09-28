package com.katyrin.bravedeveloperstest.viewmodel

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO

sealed class AppState {
    data class Success(val pokemonDTO: PokemonDTO) : AppState()
    data class Error(val errorState: ErrorState) : AppState()
    object Loading: AppState()
}