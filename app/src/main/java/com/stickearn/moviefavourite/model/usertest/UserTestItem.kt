package com.stickearn.moviefavourite.model.usertest


import com.google.gson.annotations.SerializedName

data class UserTestItem(
    @SerializedName("email")
    val email: String = "",
    @SerializedName("encrypted_password")
    val encryptedPassword: String  = "",
    @SerializedName("id")
    val id: Int  = 0,
    @SerializedName("nama")
    val nama: String  = "",
    @SerializedName("salt")
    val salt: String = "",
    @SerializedName("unique_id")
    val uniqueId: String = "",
    @SerializedName("0")
    val x0: Int = 0,
    @SerializedName("1")
    val x1: String = "",
    @SerializedName("2")
    val x2: String = "",
    @SerializedName("3")
    val x3: String = "",
    @SerializedName("4")
    val x4: String = "",
    @SerializedName("5")
    val x5: String = ""
)