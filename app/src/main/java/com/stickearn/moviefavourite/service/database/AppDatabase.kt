package com.stickearn.moviefavourite.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail
import com.stickearn.moviefavourite.service.database.dao.PopularMovieDetailDao

@Database(
    entities =[PopularMovieDetail::class], version = 5
)

@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun popularMovieDetailDao(): PopularMovieDetailDao
}