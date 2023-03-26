package com.example.facebookapi.screens.facebookDataScreens

import android.content.Context
import android.os.Bundle
import com.z1Finance.z1FinanceCore.BaseMvpPresenterImpl

class FacebookPostPresenter : BaseMvpPresenterImpl<FacebookPostView.View>(),
    FacebookPostView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}