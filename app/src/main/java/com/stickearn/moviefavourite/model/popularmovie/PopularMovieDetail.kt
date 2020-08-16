package com.stickearn.moviefavourite.model.popularmovie


import  androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class PopularMovieDetail(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String
) : Serializable