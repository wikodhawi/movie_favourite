package com.stickearn.moviefavourite.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.base.BaseViewModel
import com.stickearn.moviefavourite.model.popularmovie.PopularMovie
import com.stickearn.moviefavourite.repository.login.LoginRepository
import com.stickearn.moviefavourite.repository.login.MainRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class MainViewModel(private val mainRepository: MainRepository) : BaseViewModel() {
    val popularMovies = MutableLiveData<PopularMovie>()
    fun getPopularMovie(){
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = mainRepository.getPopularMovie()
                popularMovies.postValue(response)
                isLoading.postValue(false)
            }
            catch (e: Exception)
            {
                errorMessage.postValue(e.localizedMessage)
                isLoading.postValue(false)
            }
        }
    }
}