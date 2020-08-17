package com.stickearn.moviefavourite.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stickearn.moviefavourite.viewmodel.FavouriteMovieViewModel
import com.stickearn.moviefavourite.viewmodel.MainViewModel
import com.stickearn.moviefavourite.viewmodel.MovieDetailViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
    factory { MovieDetailViewModel(get(), get()) }
    factory { FavouriteMovieViewModel(get()) }
}


open class BaseViewModel : ViewModel() {
}