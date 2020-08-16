package com.stickearn.moviefavourite.model.moviereview


import com.google.gson.annotations.SerializedName

data class MovieReviewDetail(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)