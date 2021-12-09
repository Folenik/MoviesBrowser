package com.folen.moviesbrowser.restapi

import retrofit2.Call
import retrofit2.http.GET

interface SampleRequest {

    @GET("latest?api_key=36a9335b97de344e540bf46e01cbc67a&language=en-US")
    fun getSampleObject(): Call<SampleDataClass>
}