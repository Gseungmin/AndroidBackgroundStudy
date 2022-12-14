package com.example.coinstudy.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinstudy.dataModel.CurrentPrice
import com.example.coinstudy.dataModel.CurrentPriceResult
import com.example.coinstudy.network.model.CurrentPriceList
import com.example.coinstudy.network.repository.NetworkRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import timber.log.Timber

class SelectViewModel : ViewModel() {

    private val networkRepository = NetworkRepository()

    private lateinit var currentPriceResultList : ArrayList<CurrentPriceResult>

    fun getCurrentCoinList() = viewModelScope.launch {

        val result = networkRepository.getCurrentCoinList()

        currentPriceResultList = ArrayList()

        for (coin in result.data) {

            try {
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data.get(coin.key))
                val gsonFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)

                val currentPriceResult = CurrentPriceResult(coin.key, gsonFromJson)

                currentPriceResultList.add(currentPriceResult)
                
            } catch (e: java.lang.Exception) {
                Timber.d(e.toString())
            }
        }
    }
}