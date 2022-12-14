package com.example.coinstudy.view.main

import androidx.lifecycle.*
import com.example.coinstudy.dataModel.CurrentPrice
import com.example.coinstudy.dataModel.CurrentPriceResult
import com.example.coinstudy.dataStore.MyDataStore
import com.example.coinstudy.network.model.CurrentPriceList
import com.example.coinstudy.network.repository.DBRepository
import com.example.coinstudy.network.repository.NetworkRepository
import com.example.umc.db.InterestCoinEntity
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val dbRepository = DBRepository()

    lateinit var selectedCoinList : LiveData<List<InterestCoinEntity>>

    fun getAllInterestCoinData() = viewModelScope.launch {

        val coinList = dbRepository.getAllInterestCoinData().asLiveData()
        selectedCoinList = coinList
    }
}