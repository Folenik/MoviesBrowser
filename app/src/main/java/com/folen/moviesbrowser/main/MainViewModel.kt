package com.folen.moviesbrowser.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.folen.moviesbrowser.model.MovieDBResponse
import com.folen.moviesbrowser.restapi.MovieRepository
import kotlinx.coroutines.*

class MainViewModel constructor(
    private val movieRepository: MovieRepository,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<MovieDBResponse>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllMovies() {
        job = CoroutineScope(dispatcherIO + exceptionHandler).launch {
            val response = movieRepository.getAllMovies()
            withContext(dispatcherMain) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}