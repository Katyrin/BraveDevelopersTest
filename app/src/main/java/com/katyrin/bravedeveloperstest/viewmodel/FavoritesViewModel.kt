package com.katyrin.bravedeveloperstest.viewmodel

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.model.repository.FavoritesRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : BaseViewModel<List<PokemonDTO>>() {

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        cancelJob()
        viewModelCoroutineScope.launch {
            mutableLiveData.value = AppState.Success(favoritesRepository.getPokemonList())
        }
    }

    fun deletePokemon(pokemonDTO: PokemonDTO) {
        cancelJob()
        viewModelCoroutineScope.launch {
            favoritesRepository.deletePokemon(pokemonDTO)
            getPokemonList()
        }
    }
}