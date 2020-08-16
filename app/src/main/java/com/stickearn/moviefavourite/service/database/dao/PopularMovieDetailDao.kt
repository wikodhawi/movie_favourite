package com.stickearn.moviefavourite.service.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.stickearn.moviefavourite.model.entity.DummyEntity
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail

@Dao
interface PopularMovieDetailDao {
    @Query("SELECT * FROM PopularMovieDetail")
    fun getAll(): List<PopularMovieDetail>

    @Query("SELECT * FROM PopularMovieDetail WHERE id=:id")
    fun getById(id: String): PopularMovieDetail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg items: PopularMovieDetail?)

    @Update
    suspend fun update(vararg items: PopularMovieDetail?)

    @Delete
    suspend fun delete(item: PopularMovieDetail?)
}