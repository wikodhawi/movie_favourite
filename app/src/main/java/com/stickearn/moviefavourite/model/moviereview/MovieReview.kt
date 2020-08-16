package com.stickearn.moviefavourite.model.moviereview


import com.google.gson.annotations.SerializedName

data class MovieReview(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieReviewDetail>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)