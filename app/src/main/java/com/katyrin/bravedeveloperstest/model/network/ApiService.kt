package com.katyrin.bravedeveloperstest.model.network

import com.katyrin.bravedeveloperstest.model.entities.NamedApiResourceList
import com.katyrin.bravedeveloperstest.model.entities.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getListPokemon(): NamedApiResourceList

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Pokemon
}