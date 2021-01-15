package com.grosalex.chesslogger.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object ConfigViewModel : ViewModel() {
    val forceDarkTheme: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun switchDarkTheme() {
        forceDarkTheme.value = !(forceDarkTheme.value ?: false)
    }
}