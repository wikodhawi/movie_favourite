package com.stickearn.moviefavourite

import android.app.Application
import com.stickearn.moviefavourite.base.activityModule
import com.stickearn.moviefavourite.base.viewModelModule
import com.stickearn.moviefavourite.di.module.apiModule
import com.stickearn.moviefavourite.di.module.applicationModule
import com.stickearn.moviefavourite.di.module.databaseModule
import com.stickearn.moviefavourite.repository.login.loginRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(
                apiModule, applicationModule, databaseModule, loginRepositoryModule,  activityModule, viewModelModule
            ))
        }

    }
}