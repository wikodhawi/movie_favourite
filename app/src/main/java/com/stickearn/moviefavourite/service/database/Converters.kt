package com.stickearn.moviefavourite.service.database

import androidx.room.TypeConverter
import com.stickearn.moviefavourite.utilities.extension.fromJson
import com.stickearn.moviefavourite.utilities.extension.toJson

class Converters {
    @TypeConverter
    fun fromList(value: String?): ArrayList<String>? = value?.let { value.fromJson() }

    @TypeConverter
    fun fromString(value: ArrayList<String>?): String? = value?.let { value.toJson() }
}