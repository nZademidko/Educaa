package com.example.englishwords.ui.base

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseView, OnBackPressed {

    companion object {
        const val ARGS_KEY = "ARGUMENTS"
    }

    fun <T : Parcelable> args(): T = arguments?.getParcelable<T>(ARGS_KEY) as T

    fun <T : Parcelable> args(args: T) {
        val bundle: Bundle = Bundle().apply {
            putParcelable(ARGS_KEY, args)
        }

        arguments = bundle
    }

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?):
            View = inflater.inflate(getLayoutId(), container, false)


    open fun <T : BaseFragment> navigateTo(fragment: T): Boolean {
        return getContainer()?.navigateTo(fragment) ?: false
    }

    protected fun getContainer(): BaseContainer? {
        return activity?.let { fragmentActivity ->
            fragmentActivity.supportFragmentManager.fragments[0] as? BaseContainer
        }
    }

    /**
     * Временно используем для показа ошибок
     */

    override fun showError(message: String, duration: Int) {
        context?.let { Toast.makeText(it, message, duration).show() }
    }

    /**
     * Временно используем для показа ошибок
     */

    override fun showError(@StringRes messageId: Int, duration: Int) {
        showError(getString(messageId, duration))
    }

    override fun onBackPressed(): Boolean = false
}