package com.example.englishwords.ui.base

import com.arellomobile.mvp.MvpPresenter


open class BasePresenter<V:BaseView>: MvpPresenter<V>(){

    open fun onResume(){}
    open fun onPause(){}

}