package com.stickearn.moviefavourite.model.popularmovie


import com.google.gson.annotations.SerializedName

data class PopularMovie(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PopularMovieDetail>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)