package com.katyrin.bravedeveloperstest.model.repository

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO

interface RandomRepository {
    suspend fun savePokemon(pokemonDTO: PokemonDTO)
    suspend fun deletePokemon(pokemonDTO: PokemonDTO)
    suspend fun searchRandomPokemon(): PokemonDTO
}