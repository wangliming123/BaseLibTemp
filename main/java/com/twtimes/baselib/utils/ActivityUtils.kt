package com.twtimes.baselib.utils

import androidx.lifecycle.ViewModel

object ActivityUtils {
    val viewModelMap = mutableMapOf<Class<ViewModel>, ViewModel>()

    fun addViewModel(viewModel: ViewModel) {
        viewModelMap[viewModel.javaClass] = viewModel
    }

    fun removeViewModel(key: Class<ViewModel>) {
        viewModelMap.remove(key)
    }

    fun removeViewModel(viewModel: ViewModel) {
        viewModelMap.remove(viewModel.javaClass)
    }
}