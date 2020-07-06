package com.example.englishwords.ui.auth.login

import com.arellomobile.mvp.InjectViewState
import com.example.englishwords.ui.base.BasePresenter
import com.example.englishwords.ui.base.BaseView


interface RegistrationView: BaseView{

    fun toSMSCodeFragment(phone:String)
}

@InjectViewState
class RegistrationPresenter: BasePresenter<RegistrationView>(){

    companion object{

        private const val PHONE_NUMBER_LENGTH = 11
    }

    fun onContinuePressed(phone: String){

        //TODO РЕАЛИЗОВАТЬ ПОЛУЧЕНИЕ ОТВЕТА С СЕРВЕРА (ТАМ ЖЕ И БУДУ ПОЛУЧАТЬ ОШИБКИ)
        if(!isPhoneValid(phone)){
            viewState.showError("Введите корректный номер телефона")
            return
        }
        viewState.toSMSCodeFragment(phone)
    }

    private fun isPhoneValid(phone: String): Boolean{
        return phone.isNotBlank() &&  phone.length == PHONE_NUMBER_LENGTH
    }

}