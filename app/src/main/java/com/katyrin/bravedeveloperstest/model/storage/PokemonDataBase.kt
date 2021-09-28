package com.katyrin.bravedeveloperstest.model.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.katyrin.bravedeveloperstest.model.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokemonDataBase : RoomDatabase() {
    abstract fun getPokemonDAO(): PokemonDAO
}