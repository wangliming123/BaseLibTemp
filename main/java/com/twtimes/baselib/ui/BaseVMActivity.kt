package com.twtimes.baselib.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orhanobut.logger.Logger
import com.twtimes.baselib.common.BaseViewModel
import com.twtimes.baselib.utils.ActivityUtils

abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity() {
    lateinit var mViewModel: VM
    abstract val providerVMClass: Class<VM>
//    protected var multipleStatusView: MultipleStatusView? = null

//    protected var isRefreshFromPull = false

    override fun init() {
        initVM()
//        multipleStatusView = childMultipleStatusView()
//        multipleStatusView?.setOnClickListener {
//            when (multipleStatusView?.viewStatus) {
//                MultipleStatusView.STATUS_ERROR, MultipleStatusView.STATUS_EMPTY ->
//                    retry()
//            }
//        }
        startObserve()
    }

//    open fun childMultipleStatusView() : MultipleStatusView? = null

//    open fun retry() {
//
//    }

    open fun startObserve() {
        mViewModel.mException.observe(this, Observer { onError(it) })
    }

    private fun initVM() {
//        mViewModel = ViewModelProviders.of(this).get(providerVMClass)
        mViewModel = ViewModelProvider(this).get(providerVMClass)
        lifecycle.addObserver(mViewModel)
        ActivityUtils.addViewModel(mViewModel)
    }

    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        ActivityUtils.removeViewModel(mViewModel)
        super.onDestroy()
    }

    open fun onError(e: Throwable) {
        Logger.d("${providerVMClass.name} onError: ${e.message}")
    }
}