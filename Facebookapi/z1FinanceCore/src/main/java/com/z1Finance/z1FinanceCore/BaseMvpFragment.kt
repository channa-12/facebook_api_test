package com.z1Finance.z1FinanceCore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import java.util.*

@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>, VM : ViewDataBinding> :
    Fragment(), BaseMvpView {
    protected lateinit var mBinding: VM
    protected abstract var mPresenter: T
    protected val DISMISS_TIMEOUT = 500
    protected fun isRootIsInitialized() = this::mBinding.isInitialized
    abstract val layoutResource: Int
    val tagId = UUID.randomUUID().toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!isRootIsInitialized()) {
            mBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
            mPresenter.initViewPresenter(
                context = requireActivity(),
                savedInstanceState
            )
        }
        return mBinding.root
    }

    override fun getContext(): Context? {
        if (activity != null) {
            return requireActivity().baseContext
        }
        return null
    }

    override fun initView() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
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

    override fun onSetThem(): Int {
        return -1
    }

    override fun onLoading() {

    }

    override fun showError(error: String) {

    }

    protected fun navigationRoute(action: NavDirections) {
        try {
            findNavController().navigate(action)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun popFragmentNavigation() {
        findNavController().popBackStack()
    }


}

