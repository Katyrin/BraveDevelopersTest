package com.katyrin.bravedeveloperstest.di

import com.google.gson.GsonBuilder
import com.katyrin.bravedeveloperstest.model.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://pokeapi.co/api/v2/"

val network = module {
    factory { provideClient() }
    factory { provideApi(okHttpClient = get()) }
}

private fun provideClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

private fun provideApi(okHttpClient: OkHttpClient): ApiService =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)