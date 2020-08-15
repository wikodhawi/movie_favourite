package com.stickearn.moviefavourite.model.login


import com.google.gson.annotations.SerializedName

data class LoginUser(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("error_msg")
    val errorMessage: String,
    @SerializedName("user")
    val user: User
)