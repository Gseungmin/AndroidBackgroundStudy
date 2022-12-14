package com.example.coinstudy.view.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinstudy.dataModel.CurrentPrice
import com.example.coinstudy.dataModel.CurrentPriceResult
import com.example.coinstudy.dataStore.MyDataStore
import com.example.coinstudy.network.model.CurrentPriceList
import com.example.coinstudy.network.repository.NetworkRepository
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class IntroViewModel : ViewModel() {


    private val _first = MutableLiveData<Boolean>()
    val first : LiveData<Boolean>
        get() = _first

    fun checkFirstFlag() = viewModelScope.launch {

        delay(1000)

        val getData = MyDataStore().getFirstData()

        _first.value = getData

        Timber.d(getData.toString())
    }
}