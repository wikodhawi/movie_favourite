package com.stickearn.moviefavourite.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DummyEntity (
    @PrimaryKey var id: Long,
    var text: String
)