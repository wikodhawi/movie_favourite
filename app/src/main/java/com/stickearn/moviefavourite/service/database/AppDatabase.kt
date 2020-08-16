package com.stickearn.moviefavourite.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stickearn.moviefavourite.model.entity.DummyEntity
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail
import com.stickearn.moviefavourite.service.database.dao.DummyEntityDao
import com.stickearn.moviefavourite.service.database.dao.PopularMovieDetailDao

@Database(
    entities =[DummyEntity::class,
            PopularMovieDetail::class], version = 5
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dummyEntityDao(): DummyEntityDao
    abstract fun popularMovieDetailDao(): PopularMovieDetailDao
}