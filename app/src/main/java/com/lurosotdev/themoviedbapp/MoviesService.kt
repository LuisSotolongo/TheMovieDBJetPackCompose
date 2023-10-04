package com.lurosotdev.themoviedbapp

import retrofit2.http.GET

interface MoviesService {

    @GET("discover/movie?api_key=061b44b74471c164f4baecc2c453eb91")
    suspend fun getMovies(): MovieResult

}