package com.katyrin.bravedeveloperstest.model.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class PokemonEntity(
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val attack: Int,
    val defense: Int,
    val hp: Int,
    val imageUrl: String? = "",
    @PrimaryKey
    val id: Int
)
