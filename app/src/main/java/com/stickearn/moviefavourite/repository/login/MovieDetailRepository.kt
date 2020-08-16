package com.stickearn.moviefavourite.repository.login

import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.model.moviereview.MovieReview
import com.stickearn.moviefavourite.service.network.Api

class MovieDetailRepository (private val api: Api) {
    suspend fun getReviewMovie(id: String) : MovieReview {
        return api.getReview(id, BuildConfig.API_KEY)
    }
}