package com.stickearn.moviefavourite.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stickearn.moviefavourite.base.BaseViewModel
import com.stickearn.moviefavourite.model.moviereview.MovieReview
import com.stickearn.moviefavourite.repository.login.MovieDetailRepository
import com.stickearn.moviefavourite.service.network.ApiResult
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository) : BaseViewModel() {
    val reviewMovies = MutableLiveData<ApiResult<MovieReview>>()
    fun getReview(id: String) {
        reviewMovies.postValue(ApiResult.loading())
        viewModelScope.launch {
            try {
                val response = movieDetailRepository.getReviewMovie(id)
                reviewMovies.postValue(ApiResult.success(response))
            }
            catch (e: Exception)
            {
                reviewMovies.postValue(ApiResult.error(e.localizedMessage))
            }
        }
    }
}