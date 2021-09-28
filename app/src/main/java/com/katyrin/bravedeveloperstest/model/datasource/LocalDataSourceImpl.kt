package com.katyrin.bravedeveloperstest.model.datasource

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.model.entities.PokemonEntity
import com.katyrin.bravedeveloperstest.model.mapping.PokemonMapping
import com.katyrin.bravedeveloperstest.model.storage.PokemonDAO

class LocalDataSourceImpl(
    private val pokemonDAO: PokemonDAO,
    private val pokemonMapping: PokemonMapping
) : LocalDataSource {

    override suspend fun getPokemonByName(pokemonName: String): PokemonDTO? {
        val pokemonEntity: PokemonEntity? = pokemonDAO.getPokemonByName(pokemonName)
        return if (pokemonEntity == null) null else pokemonMapping.mapEntityToDTO(pokemonEntity)
    }

    override suspend fun savePokemon(pokemonDTO: PokemonDTO): Unit =
        pokemonDAO.putPokemon(pokemonMapping.mapDTOToEntity(pokemonDTO))

    override suspend fun deletePokemon(pokemonDTO: PokemonDTO): Unit =
        pokemonDAO.deletePokemon(pokemonDTO.name)
}