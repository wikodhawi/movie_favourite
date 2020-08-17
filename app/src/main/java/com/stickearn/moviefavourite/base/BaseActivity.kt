package com.stickearn.moviefavourite.base

import androidx.appcompat.app.AppCompatActivity
import com.stickearn.moviefavourite.view.favouritemovie.FavouriteMovieActivity
import com.stickearn.moviefavourite.view.main.activity.MainActivity
import org.koin.dsl.module

val activityModule = module {
    factory { MainActivity() }
    factory { FavouriteMovieActivity() }

}

open class BaseActivity : AppCompatActivity() {
}