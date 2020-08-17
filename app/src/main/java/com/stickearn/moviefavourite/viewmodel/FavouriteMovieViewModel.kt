package com.stickearn.moviefavourite.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stickearn.moviefavourite.base.BaseViewModel
import com.stickearn.moviefavourite.model.popularmovie.PopularMovieDetail
import com.stickearn.moviefavourite.service.database.dao.PopularMovieDetailDao
import kotlinx.coroutines.launch

class FavouriteMovieViewModel (private val popularMovieDetailDao: PopularMovieDetailDao) : BaseViewModel() {
    val allFavouriteMovie = MutableLiveData<List<PopularMovieDetail>>()
    fun getAllFavouriteMovie() {
        viewModelScope.launch {
            allFavouriteMovie.postValue(popularMovieDetailDao.getAll())
        }
    }
}