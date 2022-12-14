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
import kotlinx.coroutines.launch
import timber.log.Timber

class IntroViewModel : ViewModel() {


//    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
//    val currentPriceResult : LiveData<List<CurrentPriceResult>>
//        get() = _currentPriceResult

    fun checkFirstFlag() = viewModelScope.launch {

        val getData = MyDataStore().getFirstData()
        Timber.d(getData.toString())
    }
}