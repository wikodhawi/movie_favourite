package com.stickearn.moviefavourite.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stickearn.moviefavourite.base.BaseViewModel
import com.stickearn.moviefavourite.model.popularmovie.PopularMovie
import com.stickearn.moviefavourite.repository.login.MainRepository
import com.stickearn.moviefavourite.service.network.ApiResult
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : BaseViewModel() {
    val popularMovies = MutableLiveData<ApiResult<PopularMovie>>()
    val topRatedMovies = MutableLiveData<ApiResult<PopularMovie>>()
    val nowPlayingMovies = MutableLiveData<ApiResult<PopularMovie>>()
    fun getPopularMovie(){
        popularMovies.postValue(ApiResult.loading())
        viewModelScope.launch {
            try {
                val response = mainRepository.getPopularMovie()
                popularMovies.postValue(ApiResult.success(response))
            }
            catch (e: Exception)
            {
                popularMovies.postValue(ApiResult.error(e.localizedMessage))
            }
        }
    }

    fun getTopRatedMovies() {
        topRatedMovies.postValue(ApiResult.loading())
        viewModelScope.launch {
            try {
                val response = mainRepository.getTopRatedMovie()
                topRatedMovies.postValue(ApiResult.success(response))
            }
            catch (e: Exception)
            {
                topRatedMovies.postValue(ApiResult.error(e.localizedMessage))
            }
        }
    }

    fun getNowPlayingMovies() {
        nowPlayingMovies.postValue(ApiResult.loading())
        viewModelScope.launch {
            try {
                val response = mainRepository.getNowPlaying()
                nowPlayingMovies.postValue(ApiResult.success(response))
            }
            catch (e: Exception)
            {
                nowPlayingMovies.postValue(ApiResult.error(e.localizedMessage))
            }
        }
    }
}