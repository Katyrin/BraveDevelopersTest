package com.katyrin.bravedeveloperstest.di

import com.katyrin.bravedeveloperstest.model.datasource.LocalDataSource
import com.katyrin.bravedeveloperstest.model.datasource.LocalDataSourceImpl
import com.katyrin.bravedeveloperstest.model.datasource.RemoteDataSource
import com.katyrin.bravedeveloperstest.model.datasource.RemoteDataSourceImpl
import com.katyrin.bravedeveloperstest.model.mapping.PokemonMapping
import com.katyrin.bravedeveloperstest.model.mapping.PokemonMappingImpl
import com.katyrin.bravedeveloperstest.model.repository.*
import com.katyrin.bravedeveloperstest.viewmodel.FavoritesViewModel
import com.katyrin.bravedeveloperstest.viewmodel.RandomViewModel
import com.katyrin.bravedeveloperstest.viewmodel.SearchViewModel
import org.koin.dsl.module

val application = module {
    single<PokemonMapping> { PokemonMappingImpl() }
    single<RemoteDataSource> { RemoteDataSourceImpl(apiService = get(), pokemonMapping = get()) }
    single<LocalDataSource> { LocalDataSourceImpl(pokemonDAO = get(), pokemonMapping = get()) }

}

val searchModule = module {
    single<SearchRepository> {
        SearchRepositoryImpl(remoteDataSource = get(), localDataSource = get())
    }
    factory { SearchViewModel(searchRepository = get()) }
}

val randomModule = module {
    single<RandomRepository> {
        RandomRepositoryImpl(remoteDataSource = get(), localDataSource = get())
    }
    factory { RandomViewModel(randomRepository = get()) }
}

val favoritesModule = module {
    single<FavoritesRepository> { FavoritesRepositoryImpl(localDataSource = get()) }
    factory { FavoritesViewModel(favoritesRepository = get()) }
}