package com.stickearn.moviefavourite.repository.login

import com.stickearn.moviefavourite.BuildConfig
import com.stickearn.moviefavourite.model.login.LoginUser
import com.stickearn.moviefavourite.model.popularmovie.PopularMovie
import com.stickearn.moviefavourite.service.network.Api
import org.koin.dsl.module

val repositoryModule = module {
    factory { LoginRepository(get()) }
    factory { MainRepository(get()) }
}

class MainRepository (private val api: Api) {
    suspend fun getPopularMovie() : PopularMovie {
        return api.getPopularMovie(BuildConfig.API_KEY)
    }
}