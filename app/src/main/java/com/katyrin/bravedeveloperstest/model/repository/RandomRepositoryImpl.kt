package com.katyrin.bravedeveloperstest.model.repository

import com.katyrin.bravedeveloperstest.model.datasource.LocalDataSource
import com.katyrin.bravedeveloperstest.model.datasource.RemoteDataSource
import com.katyrin.bravedeveloperstest.model.entities.NamedApiResourceList
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RandomRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RandomRepository {

    override suspend fun savePokemon(pokemonDTO: PokemonDTO): Unit =
        withContext(Dispatchers.IO) {
            localDataSource.savePokemon(pokemonDTO)
        }

    override suspend fun deletePokemon(pokemonDTO: PokemonDTO): Unit =
        withContext(Dispatchers.IO) {
            localDataSource.deletePokemon(pokemonDTO)
        }

    override suspend fun searchRandomPokemon(): PokemonDTO =
        withContext(Dispatchers.IO) {
            val namedApiResourceList: NamedApiResourceList = remoteDataSource.getListPokemon()
            val randomPokemonNumber: Int = (0 until namedApiResourceList.count).random()
            val pokemonName: String = namedApiResourceList.results[randomPokemonNumber].name
            val pokemonDTO: PokemonDTO? = localDataSource.getPokemonByName(pokemonName)
            pokemonDTO ?: remoteDataSource.getPokemonByName(pokemonName)
        }
}