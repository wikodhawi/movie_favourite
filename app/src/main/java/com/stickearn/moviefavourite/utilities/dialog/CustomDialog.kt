package com.stickearn.moviefavourite.utilities.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import com.bumptech.glide.Glide
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.databinding.DialogLoadingBinding

object CustomDialog {
    fun showLoadingDialog(
        activity: Activity,
        cancelable: Boolean
    ): Dialog {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = DialogLoadingBinding.inflate(LayoutInflater.from(activity))
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(cancelable)

        Glide.with(activity).asGif().load(R.raw.ic_busy_loading).into(binding.imgGif)

        return dialog
    }

}
enum class StateDialog{
    WARNING,
    ERROR,
    SUCCESS
}