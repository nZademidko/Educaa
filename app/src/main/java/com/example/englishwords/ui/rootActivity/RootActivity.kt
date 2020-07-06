package com.example.englishwords.ui.rootActivity

import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.englishwords.R
import com.example.englishwords.ui.auth.login.LoginFragment
import com.example.englishwords.ui.base.BaseContainer
import com.example.englishwords.ui.base.OnBackPressed

class RootActivity : MvpAppCompatActivity(), RootView{

    companion object{

        private const val BASE_CONTAINER_TAG = "BASE_CONTAINER"
    }

    @InjectPresenter
    lateinit var presenter: RootPresenter

    private var rootContainer: RootContainer? = null
    private var lastExitTime : Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createBaseContainer()
    }

    override fun onResume() {
        super.onResume()
        navigateToFirstFragment()
    }

    private fun navigateToFirstFragment(){
            rootContainer?.restoreFragmentsWithNavigate(LoginFragment.newInstance())
    }



    override fun showError(message: String, duration: Int) {

    }

    override fun showError(messageId: Int, duration: Int) {
    }



    override fun onBackPressed() {


        supportFragmentManager.fragments.firstOrNull()?.let {


            if(it is BaseContainer){
                it.childFragmentManager.fragments.firstOrNull()?.let{fragment ->
                    if(fragment.isVisible && fragment is OnBackPressed){
                        if(!fragment.onBackPressed()) exitFromApp()
                        
                    }
                }
            }
        }
    }

    private fun exitFromApp(){
        if(lastExitTime != 0L && SystemClock.elapsedRealtime()-lastExitTime < 1000){
            finish()
        } else{
            lastExitTime = SystemClock.elapsedRealtime()
            Toast.makeText(this,"Нажмите еще раз, чтобы выйти", Toast.LENGTH_LONG).show()
        }
    }
    private fun createBaseContainer() {

        rootContainer = supportFragmentManager.findFragmentByTag(BASE_CONTAINER_TAG) as? RootContainer

        if(rootContainer == null){
            rootContainer = RootContainer()
        }


        val transaction =  supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flContainer, rootContainer!!, BASE_CONTAINER_TAG)
        transaction.commit()

    }

}
