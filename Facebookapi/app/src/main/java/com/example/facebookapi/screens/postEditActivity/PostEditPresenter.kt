package com.example.facebookapi.screens.postEditActivity

import android.content.Context
import android.os.Bundle
import com.z1Finance.z1FinanceCore.BaseMvpPresenterImpl

class PostEditPresenter : BaseMvpPresenterImpl<PostEditView.View>(),
    PostEditView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}