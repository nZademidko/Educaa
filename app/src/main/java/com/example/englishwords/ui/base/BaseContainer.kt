package com.example.englishwords.ui.base

import androidx.fragment.app.Fragment
import com.example.englishwords.R


abstract  class BaseContainer : BaseFragment(){
    override fun getLayoutId(): Int {
        return R.layout.fragment_container
    }

    override fun <T: BaseFragment> navigateTo(fragment: T): Boolean{
        val tag = "${fragment::class.java.name}_${fragment.arguments.toString()}"
        val targetFragment: Fragment = childFragmentManager.findFragmentByTag(tag)?: fragment


        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,targetFragment,tag)
        transaction.addToBackStack(tag)
        transaction.commit()

        return fragment != targetFragment
    }


    fun <T: BaseFragment>restoreFragmentsWithNavigate(fragment: T): Boolean{
       return (childFragmentManager.fragments.lastOrNull() as? BaseFragment)?.let {lastFragment ->
                navigateTo(lastFragment)
            }?: navigateTo(fragment)
    }
}