package com.lurosotdev.themoviedbapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel() : ViewModel() {
    var state = UiState()

    init {
        viewModelScope.launch {
            state = UiState(
                Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MoviesService::class.java)
                    .getMovies()
                    .results
            )
        }
    }

    data class UiState(
        val movies: List<ServerMovie> = emptyList()
    )


}