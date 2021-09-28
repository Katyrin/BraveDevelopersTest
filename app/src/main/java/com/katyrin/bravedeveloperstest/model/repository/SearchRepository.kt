package com.katyrin.bravedeveloperstest.model.repository

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO

interface SearchRepository {
    suspend fun getPokemonByName(pokemonName: String): PokemonDTO
    suspend fun savePokemon(pokemonDTO: PokemonDTO)
    suspend fun deletePokemon(pokemonDTO: PokemonDTO)
}