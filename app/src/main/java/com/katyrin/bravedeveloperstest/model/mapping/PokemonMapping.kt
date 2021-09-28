package com.katyrin.bravedeveloperstest.model.mapping

import com.katyrin.bravedeveloperstest.model.entities.Pokemon
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.model.entities.PokemonEntity

interface PokemonMapping {
    fun mapPokemonToPokemonDTO(pokemon: Pokemon): PokemonDTO
    fun mapEntityToDTO(pokemonEntity: PokemonEntity): PokemonDTO
    fun mapDTOToEntity(pokemonDTO: PokemonDTO): PokemonEntity
    fun mapEntityListToDTOList(pokemonEntityList: List<PokemonEntity>): List<PokemonDTO>
}