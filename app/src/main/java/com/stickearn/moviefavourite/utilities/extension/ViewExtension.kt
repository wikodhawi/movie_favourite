package com.stickearn.moviefavourite.utilities.extension

import android.os.SystemClock
import android.view.View

fun View.setOnSafeClickListener(listener: (view: View) -> Unit) {
    var lastClickTime = 0L
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000)
            return@setOnClickListener
        lastClickTime = SystemClock.elapsedRealtime()
        listener.invoke(it)
    }
}