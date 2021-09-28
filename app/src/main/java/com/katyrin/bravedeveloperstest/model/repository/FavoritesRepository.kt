package com.katyrin.bravedeveloperstest.model.repository

import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO

interface FavoritesRepository {
    suspend fun getPokemonList(): List<PokemonDTO>
    suspend fun deletePokemon(pokemonDTO: PokemonDTO)
}