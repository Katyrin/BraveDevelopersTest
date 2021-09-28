package com.katyrin.bravedeveloperstest.model.repository

import com.katyrin.bravedeveloperstest.model.datasource.LocalDataSource
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesRepositoryImpl(
    private val localDataSource: LocalDataSource
) : FavoritesRepository {

    override suspend fun getPokemonList(): List<PokemonDTO> =
        withContext(Dispatchers.IO) { localDataSource.getPokemonList() }

    override suspend fun deletePokemon(pokemonDTO: PokemonDTO): Unit =
        withContext(Dispatchers.IO) { localDataSource.deletePokemon(pokemonDTO) }
}