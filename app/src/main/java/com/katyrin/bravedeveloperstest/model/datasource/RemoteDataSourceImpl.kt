package com.katyrin.bravedeveloperstest.model.datasource

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.model.mapping.PokemonMapping
import com.katyrin.bravedeveloperstest.model.network.ApiService

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private val pokemonMapping: PokemonMapping
) : RemoteDataSource {

    override suspend fun getPokemonByName(pokemonName: String): PokemonDTO =
        pokemonMapping.mapPokemonToPokemonDTO(apiService.getPokemonByName(pokemonName))
}