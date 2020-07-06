package com.example.englishwords.ui.rootActivity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.englishwords.ui.base.BasePresenter
import com.example.englishwords.ui.base.BaseView

interface RootView: BaseView {


}

@InjectViewState
class RootPresenter : BasePresenter<RootView>(){

}