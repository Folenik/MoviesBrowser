package com.folen.moviesbrowser.restapi

import com.folen.moviesbrowser.model.MovieDBResponse
import com.folen.moviesbrowser.utilities.ConstantValues
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitClient {
    companion object {
        const val getMoviesRequest =
            "now_playing?api_key=${ConstantValues.API_KEY}&language${ConstantValues.DEFAULT_LANGUAGE}&page=${ConstantValues.PAGE_REQUEST}"
        var retrofitService: RetrofitClient? = null
        fun getInstance(): RetrofitClient {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ConstantValues.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitClient::class.java)
            }
            return retrofitService!!
        }
    }

    @GET(getMoviesRequest)
    suspend fun getMovies(): Response<MovieDBResponse>
}