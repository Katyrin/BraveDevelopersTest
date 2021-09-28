package com.katyrin.bravedeveloperstest.model.storage

import androidx.room.*
import com.katyrin.bravedeveloperstest.model.entities.PokemonEntity

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM PokemonEntity ORDER BY id")
    suspend fun getPokemonList(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putPokemon(pokemonEntity: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity WHERE name LIKE :name")
    suspend fun getPokemonByName(name: String): PokemonEntity?

    @Query("DELETE FROM PokemonEntity WHERE name = :name")
    suspend fun deletePokemon(name: String)
}