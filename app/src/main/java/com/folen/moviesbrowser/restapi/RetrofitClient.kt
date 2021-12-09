package com.folen.moviesbrowser.restapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private lateinit var retrofit: Retrofit
    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

    fun getService(): SampleRequest {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(SampleRequest::class.java)
    }
}