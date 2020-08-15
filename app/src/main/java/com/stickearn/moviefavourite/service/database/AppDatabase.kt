package com.stickearn.moviefavourite.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stickearn.moviefavourite.model.entity.DummyEntity
import com.stickearn.moviefavourite.service.database.dao.DummyEntityDao

@Database(
    entities =[DummyEntity::class], version = 3
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dummyEntityDao(): DummyEntityDao
}