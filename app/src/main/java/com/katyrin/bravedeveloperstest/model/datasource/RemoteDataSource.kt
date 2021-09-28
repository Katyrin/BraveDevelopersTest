package com.katyrin.bravedeveloperstest.model.datasource

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO

interface RemoteDataSource {
    suspend fun getPokemonByName(pokemonName: String): PokemonDTO
}