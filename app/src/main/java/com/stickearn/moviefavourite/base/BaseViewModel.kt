package com.stickearn.moviefavourite.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stickearn.moviefavourite.viewmodel.LoginViewModel
import com.stickearn.moviefavourite.viewmodel.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { LoginViewModel(get(), get()) }
    factory { MainViewModel(get()) }
}


open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
}