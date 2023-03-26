package com.example.facebookapi.screens.postEditActivity

import android.content.Context
import android.os.Bundle
import com.z1Finance.z1FinanceCore.BaseMvpPresenter
import com.z1Finance.z1FinanceCore.BaseMvpView

class PostEditView {
    interface View : BaseMvpView {
        override fun initView()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
    }
}