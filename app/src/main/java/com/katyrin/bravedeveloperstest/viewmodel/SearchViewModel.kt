package com.katyrin.bravedeveloperstest.viewmodel

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.model.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository
) : BaseViewModel() {

    private var currentPokemon: PokemonDTO? = null

    fun searchPokemonByName(pokemonName: String) {
        mutableLiveData.value = AppState.Loading
        cancelJob()
        viewModelCoroutineScope.launch {
            val pokemonDTO: PokemonDTO = searchRepository.getPokemonByName(pokemonName)
            currentPokemon = pokemonDTO
            mutableLiveData.value = AppState.Success(pokemonDTO)
        }
    }

    fun changeFavoriteStatus(isChecked: Boolean) {
        viewModelCoroutineScope.launch {
            currentPokemon?.let { pokemonDTO ->
                if (isChecked) searchRepository.savePokemon(pokemonDTO)
                else searchRepository.deletePokemon(pokemonDTO)
            }
        }
    }

    override fun onCleared() {
        currentPokemon = null
        super.onCleared()
    }
}