package com.katyrin.bravedeveloperstest.model.repository

import com.katyrin.bravedeveloperstest.model.datasource.LocalDataSource
import com.katyrin.bravedeveloperstest.model.datasource.RemoteDataSource
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : SearchRepository {

    override suspend fun getPokemonByName(pokemonName: String): PokemonDTO =
        withContext(Dispatchers.IO) {
            val pokemonDTO: PokemonDTO? = localDataSource.getPokemonByName(pokemonName)
            pokemonDTO ?: remoteDataSource.getPokemonByName(pokemonName)
        }

    override suspend fun savePokemon(pokemonDTO: PokemonDTO): Unit =
        localDataSource.savePokemon(pokemonDTO)

    override suspend fun deletePokemon(pokemonDTO: PokemonDTO): Unit =
        localDataSource.deletePokemon(pokemonDTO)
}