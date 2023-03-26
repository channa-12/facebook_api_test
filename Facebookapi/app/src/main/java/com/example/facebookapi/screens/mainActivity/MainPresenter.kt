package com.example.facebookapi.screens.mainActivity

import android.content.Context
import android.os.Bundle
import com.z1Finance.z1FinanceCore.BaseMvpPresenterImpl

class MainPresenter : BaseMvpPresenterImpl<MainView.View>(),
    MainView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}