package com.stickearn.moviefavourite.repository.login

import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.model.popularmovie.PopularMovie
import com.stickearn.moviefavourite.service.network.Api
import org.koin.dsl.module

val repositoryModule = module {
    factory { MainRepository(get()) }
    factory { MovieDetailRepository(get()) }
}

class MainRepository (private val api: Api) {
    suspend fun getPopularMovie() : PopularMovie {
        return api.getPopularMovie(BuildConfig.API_KEY)
    }

    suspend fun getTopRatedMovie() : PopularMovie {
        return api.getTopRatedMovie(BuildConfig.API_KEY)
    }

    suspend fun getNowPlaying() : PopularMovie {
        return api.getNowPlaying(BuildConfig.API_KEY)
    }
}