package com.z1Finance.z1FinanceCore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by nemo on 9/28/2017.
 * Copyright © 2022 Z1finance. All rights reserved.
 */

abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>, M : ViewDataBinding>
    : AppCompatActivity(), BaseMvpView {
    protected val DISMISS_TIMEOUT = 500
    abstract val layoutResource: Int
    protected lateinit var binding: M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (onSetThem() != -1) {
            setTheme(onSetThem())
        }
        this.mPresenter.attachView(this@BaseMvpActivity as V)
        binding = DataBindingUtil.setContentView(this, layoutResource)
        mPresenter.initViewPresenter(this, savedInstanceState)
    }

    override fun getContext(): Context = this

    override fun initView() {

    }

    protected abstract var mPresenter: T

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onNext(page: Int) {

    }

    override fun onWillBeHidden() {

    }

    override fun onWillBeDisplayed() {

    }

    override fun onRefresh() {

    }

    override fun onSuccess(any: Any) {

    }

    override fun onFail(any: Any) {

    }

    override fun onLoading() {

    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    override fun onSetThem(): Int {
        return -1
    }

}