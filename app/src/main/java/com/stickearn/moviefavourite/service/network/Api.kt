package com.stickearn.moviefavourite.service.network

import com.stickearn.moviefavourite.model.login.LoginUser
import com.stickearn.moviefavourite.model.usertest.UserTest
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface Api {
    @Multipart
    @POST("crudtest/login.php")
    suspend fun loginWorker(@Part("email") email: RequestBody,
                            @Part("password") password: RequestBody): LoginUser

    @GET("crudtest/getAllUser.php")
    suspend fun getAllUser(): UserTest
}
