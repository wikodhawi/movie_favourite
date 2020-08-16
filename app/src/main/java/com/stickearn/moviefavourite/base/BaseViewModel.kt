package com.stickearn.moviefavourite.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stickearn.moviefavourite.viewmodel.LoginViewModel
import com.stickearn.moviefavourite.viewmodel.MainViewModel
import com.stickearn.moviefavourite.viewmodel.MovieDetailViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { LoginViewModel(get(), get()) }
    factory { MainViewModel(get()) }
    factory { MovieDetailViewModel(get()) }
}


open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
}