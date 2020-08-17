package com.stickearn.moviefavourite.service.network

import com.stickearn.moviefavourite.model.moviereview.MovieReview
import com.stickearn.moviefavourite.model.popularmovie.PopularMovie
import retrofit2.http.*


interface Api {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String): PopularMovie

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("api_key") apiKey: String): PopularMovie

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey: String): PopularMovie

    @GET("movie/{id}/reviews")
    suspend fun getReview(@Path("id") id: String,
        @Query("api_key") apiKey: String): MovieReview

}
