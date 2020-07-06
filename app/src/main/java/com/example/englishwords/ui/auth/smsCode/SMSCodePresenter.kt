package com.example.englishwords.ui.auth.smsCode

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.englishwords.ui.base.BasePresenter
import com.example.englishwords.ui.base.BaseView


interface SMSCodeView: BaseView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSMSCodeError(show: Boolean, errorMessage: String = "")

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(show: Boolean)
}

@InjectViewState
class SMSCodePresenter(private val phone: String): BasePresenter<SMSCodeView>(){

    fun onResendCodePressed(){
        //TODO РЕАЛИЗОВАТЬ ПОЛУЧЕНИЕ ОТВЕТА ПОСЛЕ ВВЕДЕНИЯ КОДА ИЗ СМС
        viewState.showLoading(false)

    }

    fun onSMSCodeInsert(smsCode: String){

        if(smsCode == "1234"){
            viewState.showSMSCodeError(true, "Вы ввели неправильно код")
        }else{
            viewState.showSMSCodeError(false)
            viewState.showLoading(true)
        }

    }

}