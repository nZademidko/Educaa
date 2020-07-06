package com.example.englishwords.ui.base

import android.widget.Toast
import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpView

interface BaseView : MvpView {

    fun showError(message: String, duration: Int = Toast.LENGTH_LONG)

    fun showError(@StringRes messageId: Int, duration: Int= Toast.LENGTH_LONG)
}