package com.katyrin.bravedeveloperstest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    protected val mutableLiveData: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState>
        get() = mutableLiveData

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable -> handlerError(throwable) }
    )

    private fun handlerError(throwable: Throwable) {
        mutableLiveData.value = when (throwable) {
            is HttpException -> AppState.Error(ErrorState.NotFound)
            is SocketTimeoutException -> AppState.Error(ErrorState.TimOut)
            is UnknownHostException -> AppState.Error(ErrorState.UnknownHost)
            is ConnectionShutdownException -> AppState.Error(ErrorState.Connection)
            is IOException -> AppState.Error(ErrorState.Server)
            else -> AppState.Error(ErrorState.Unknown(throwable.message))
        }
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        cancelJob()
        super.onCleared()
    }
}