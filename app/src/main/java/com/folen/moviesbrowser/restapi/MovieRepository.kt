package com.folen.moviesbrowser.restapi

class MovieRepository constructor(private val retrofitClient: RetrofitClient) {
    suspend fun getAllMovies() = retrofitClient.getMovies()
}
