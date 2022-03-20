package com.folen.moviesbrowser.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDBResponse(
    @SerializedName("dates")
    @Expose
    val dates: Dates,

    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val movie: List<Movie>,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int
)