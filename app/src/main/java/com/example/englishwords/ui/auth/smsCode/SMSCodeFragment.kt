package com.example.englishwords.ui.auth.smsCode

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.englishwords.R
import com.example.englishwords.ui.auth.login.LoginFragment
import com.example.englishwords.ui.base.BaseFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_sms_code.*
import java.lang.StringBuilder


class SMSCodeFragment : BaseFragment(), SMSCodeView {

    companion object {
        fun newInstance(): SMSCodeFragment = SMSCodeFragment()
    }

    @InjectPresenter
    lateinit var presenter: SMSCodePresenter

    @ProvidePresenter
    fun providePresenter(): SMSCodePresenter {
        return SMSCodePresenter(args<SMSCodeArgs>().phone)
    }

    override fun getLayoutId(): Int = R.layout.fragment_sms_code

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEtCode()
        initListeners()
    }

    private val textChangeListener: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            if (p0?.isEmpty() == true) return


            if (currentCodeIndex == edCodes.size - 1) {
                val smsCodeBuilder = StringBuilder()
                edCodes.forEach {
                    smsCodeBuilder.append(it?.text.toString())
                }
                presenter.onSMSCodeInsert(smsCodeBuilder.toString())
            } else {
                currentCodeIndex++
                edCodes[currentCodeIndex]?.requestFocus()
            }
        }
    }

    private val focusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
        if (hasFocus) {
            edCodes.forEachIndexed { index, editText ->
                if (editText == view) {
                    if (currentCodeIndex != index) {
                        currentCodeIndex = index
                    }
                }

            }
        }
    }

    private var currentCodeIndex = 0
    private var edCodes = arrayListOf<EditText?>()


    private fun initEtCode() {

        edCodes.add(etCodeOne)
        edCodes.add(etCodeTwo)
        edCodes.add(etCodeThree)
        edCodes.add(etCodeFour)

    }

    private fun initListeners() {
        btnResendCode?.setOnClickListener {
            presenter.onResendCodePressed()
        }

        edCodes.forEach {
            it?.addTextChangedListener(textChangeListener)
            it?.onFocusChangeListener = focusChangeListener
        }


    }

    override fun showSMSCodeError(show: Boolean, errorMessage: String) {

        if (show) {
            showError(errorMessage)
        }
    }

    override fun onBackPressed(): Boolean {

        return true;
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            pbLoading.visibility = View.VISIBLE
            edCodes.forEach {
                it?.isEnabled = false
            }
            btnResendCode?.isEnabled = false
        } else {

            pbLoading.visibility = View.GONE
            edCodes.forEach {
                it?.isEnabled = true
            }
            btnResendCode?.isEnabled = true
        }
    }

    @Parcelize
    data class SMSCodeArgs(val phone: String) : Parcelable

}