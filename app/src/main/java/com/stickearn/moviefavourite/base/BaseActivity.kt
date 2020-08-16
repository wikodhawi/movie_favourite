package com.stickearn.moviefavourite.base

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.stickearn.moviefavourite.utilities.dialog.CustomDialog
import com.stickearn.moviefavourite.view.favouritemovie.FavouriteMovieActivity
import com.stickearn.moviefavourite.view.login.LoginActivity
import com.stickearn.moviefavourite.view.main.activity.MainActivity
import org.koin.dsl.module

val activityModule = module {
    factory { LoginActivity() }
    factory { MainActivity() }
    factory { FavouriteMovieActivity() }

}

open class BaseActivity : AppCompatActivity() {
    fun loadingDialog(activity: Activity) = CustomDialog.showLoadingDialog(activity, true)
}