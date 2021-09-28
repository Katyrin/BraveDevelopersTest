package com.katyrin.bravedeveloperstest.model.datasource

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO

interface LocalDataSource {
    suspend fun getPokemonByName(pokemonName: String): PokemonDTO?
    suspend fun savePokemon(pokemonDTO: PokemonDTO)
    suspend fun deletePokemon(pokemonDTO: PokemonDTO)
    suspend fun getPokemonList(): List<PokemonDTO>
}