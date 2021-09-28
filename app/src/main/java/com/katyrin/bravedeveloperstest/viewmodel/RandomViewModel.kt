package com.katyrin.bravedeveloperstest.viewmodel

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.model.repository.RandomRepository
import kotlinx.coroutines.launch

class RandomViewModel(
    private val randomRepository: RandomRepository
) : BaseViewModel() {

    private var currentPokemon: PokemonDTO? = null

    fun changeFavoriteStatus(isChecked: Boolean) {
        viewModelCoroutineScope.launch {
            currentPokemon?.let { pokemonDTO ->
                if (isChecked) randomRepository.savePokemon(pokemonDTO)
                else randomRepository.deletePokemon(pokemonDTO)
            }
        }
    }

    fun searchRandomPokemon() {
        mutableLiveData.value = AppState.Loading
        cancelJob()
        viewModelCoroutineScope.launch {
            val pokemonDTO: PokemonDTO = randomRepository.searchRandomPokemon()
            currentPokemon = pokemonDTO
            mutableLiveData.value = AppState.Success(pokemonDTO)
        }
    }

    override fun onCleared() {
        currentPokemon = null
        super.onCleared()
    }
}