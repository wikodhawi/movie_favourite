package com.stickearn.moviefavourite.di.module

import android.app.Application
import android.content.Context
import com.stickearn.moviefavourite.MainApplication
import com.stickearn.moviefavourite.utilities.GlobalVar
import com.stickearn.moviefavourite.utilities.PrefHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {
    single { ApplicationModule().prefHelper(androidContext()) }
    single { ApplicationModule().provideMainApplication(androidApplication()) }
}

class ApplicationModule {
    fun provideContext(application: Application): Context = application

    fun provideMainApplication(application: Application): MainApplication = application as MainApplication

    fun prefHelper(context: Context) : PrefHelper {
        PrefHelper.setSharedPreferences(context, GlobalVar.sharedPreferencesName, Context.MODE_PRIVATE)
        return PrefHelper()
    }

}