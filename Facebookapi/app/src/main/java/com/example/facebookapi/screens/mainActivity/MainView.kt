package com.example.facebookapi.screens.mainActivity

import android.content.Context
import android.os.Bundle
import com.z1Finance.z1FinanceCore.BaseMvpPresenter
import com.z1Finance.z1FinanceCore.BaseMvpView

class MainView {
    interface View : BaseMvpView {
        override fun initView()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
    }
}