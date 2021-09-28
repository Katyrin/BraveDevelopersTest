package com.katyrin.bravedeveloperstest.model.entities

import com.google.gson.annotations.SerializedName


data class Pokemon(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val species: NamedApiResource,
    val abilities: List<PokemonAbility>,
    val forms: List<NamedApiResource>,
    val gameIndices: List<VersionGameIndex>,
    val heldItems: List<PokemonHeldItem>,
    val moves: List<PokemonMove>,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>,
    val sprites: PokemonSprites
)

data class PokemonSprites(
    val backDefault: String?,
    val backShiny: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    val frontShiny: String?,
    val backFemale: String?,
    val backShinyFemale: String?,
    val frontFemale: String?,
    val frontShinyFemale: String?

)

data class PokemonAbility(
    val isHidden: Boolean,
    val slot: Int,
    val ability: NamedApiResource
)

data class PokemonHeldItem(
    val item: NamedApiResource,
    val versionDetails: List<PokemonHeldItemVersion>
)

data class PokemonHeldItemVersion(
    val version: NamedApiResource,
    val rarity: Int
)

data class PokemonMove(
    val move: NamedApiResource,
    val versionGroupDetails: List<PokemonMoveVersion>
)

data class PokemonMoveVersion(
    val moveLearnMethod: NamedApiResource,
    val versionGroup: NamedApiResource,
    val levelLearnedAt: Int
)

data class PokemonStat(
    val stat: NamedApiResource,
    val effort: Int,
    @SerializedName("base_stat")
    val baseStat: Int
)

data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)

data class VersionGameIndex(
    val gameIndex: Int,
    val version: NamedApiResource
)