package com.stickearn.moviefavourite.service.network

import com.stickearn.moviefavourite.model.login.LoginUser
import com.stickearn.moviefavourite.model.popularmovie.PopularMovie
import com.stickearn.moviefavourite.model.usertest.UserTest
import okhttp3.RequestBody
import retrofit2.http.*


interface Api {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String): PopularMovie
}
