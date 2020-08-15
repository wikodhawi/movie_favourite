package com.stickearn.moviefavourite.model.login


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("nama")
    val nama: String
)