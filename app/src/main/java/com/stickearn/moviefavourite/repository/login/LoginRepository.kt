package com.stickearn.moviefavourite.repository.login

import com.stickearn.moviefavourite.model.login.LoginUser
import com.stickearn.moviefavourite.service.network.Api
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.dsl.module

val loginRepositoryModule = module {
    factory { LoginRepository(get()) }
}

class LoginRepository (private val api: Api) {
    suspend fun loginApi(email: String, password: String) : LoginUser {
        val userNameB:RequestBody=
            email.toRequestBody(email.toMediaTypeOrNull())
        val passwordB: RequestBody =
            password.toRequestBody(password.toMediaTypeOrNull())
        return api.loginWorker(userNameB, passwordB)
    }
}