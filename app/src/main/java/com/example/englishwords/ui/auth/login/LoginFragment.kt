package com.example.englishwords.ui.auth.login

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.englishwords.R
import com.example.englishwords.ui.auth.smsCode.SMSCodeFragment
import com.example.englishwords.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment: BaseFragment(), RegistrationView{

    companion object{
        fun newInstance() : LoginFragment = LoginFragment()
    }

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners(){
        btnLoginWithPhone?.setOnClickListener(){
            presenter.onBtnLoginPhoneContinuePressed(etPhone?.text?.toString() ?: "")
        }

        btnLoginWithGoogle?.setOnClickListener(){
            presenter.onBtnLoginGooglePressed()
            showError("Google")
        }

        btnLoginWithVk?.setOnClickListener(){
            presenter.onBtnLoginVkPressed()
            showError("Vk")
        }

        btnLoginWithInstagram?.setOnClickListener(){
            presenter.onBtnLoginInstagramPressed()
            showError("Instagram")
        }

        tvConfCond?.setOnClickListener {
            presenter.onTVConfCondPressed()
            showError("Условия конфиденциальности")
        }

        tvUserAgreement.setOnClickListener {
            presenter.onTVUserAgreementPressed()
            showError("Пользовательское соглашение")
        }
    }


    override fun onBackPressed(): Boolean {

        return false
    }

    override fun toSMSCodeFragment(phone: String) {

        navigateTo(SMSCodeFragment.newInstance().apply {
            args(SMSCodeFragment.SMSCodeArgs(phone))
        })
    }

}