package com.katyrin.bravedeveloperstest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.katyrin.bravedeveloperstest.model.entities.PokemonDTO
import com.katyrin.bravedeveloperstest.model.repository.SearchRepository
import kotlinx.coroutines.*
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private var currentPokemon: PokemonDTO? = null

    private val _mutableLiveData: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState>
        get() = _mutableLiveData

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable -> handlerError(throwable) }
    )

    private fun handlerError(throwable: Throwable) {
        _mutableLiveData.value = when (throwable) {
            is HttpException -> AppState.Error(ErrorState.NotFound)
            is SocketTimeoutException -> AppState.Error(ErrorState.TimOut)
            is UnknownHostException -> AppState.Error(ErrorState.UnknownHost)
            is ConnectionShutdownException -> AppState.Error(ErrorState.Connection)
            is IOException -> AppState.Error(ErrorState.Server)
            else -> AppState.Error(ErrorState.Unknown(throwable.message))
        }
    }

    fun searchPokemonByName(pokemonName: String) {
        _mutableLiveData.value = AppState.Loading
        cancelJob()
        viewModelCoroutineScope.launch {
            val pokemonDTO: PokemonDTO = searchRepository.getPokemonByName(pokemonName)
            currentPokemon = pokemonDTO
            _mutableLiveData.value = AppState.Success(pokemonDTO)
        }
    }

    fun changeFavoriteStatus(isChecked: Boolean) {
        viewModelCoroutineScope.launch {
            currentPokemon?.let { pokemonDTO ->
                if (isChecked) searchRepository.savePokemon(pokemonDTO)
                else searchRepository.deletePokemon(pokemonDTO)
            }
        }
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        cancelJob()
        currentPokemon = null
        super.onCleared()
    }
}