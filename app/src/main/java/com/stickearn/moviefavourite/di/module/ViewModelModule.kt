package com.stickearn.moviefavourite.di.module

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.bintangtoedjoe.recruitment.di.factory.ViewModelFactory
//import com.bintangtoedjoe.recruitment.di.scope.ViewModelKey
//import com.bintangtoedjoe.recruitment.viewmodel.LoginViewModel
//import com.bintangtoedjoe.recruitment.viewmodel.MainViewModel
//import dagger.Binds
//import dagger.Module
//import dagger.multibindings.IntoMap
//
//@Module
//abstract class ViewModelModule {
//    @Binds
//    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
//}