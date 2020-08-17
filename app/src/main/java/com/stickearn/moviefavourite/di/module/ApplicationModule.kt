package com.stickearn.moviefavourite.di.module

import android.app.Application
import com.stickearn.moviefavourite.MainApplication
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val applicationModule = module {
    single { ApplicationModule().provideMainApplication(androidApplication()) }
}

class ApplicationModule {

    fun provideMainApplication(application: Application): MainApplication = application as MainApplication
}