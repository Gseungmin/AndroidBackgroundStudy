package com.example.coinstudy.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinstudy.dataModel.CurrentPrice
import com.example.coinstudy.dataModel.CurrentPriceResult
import com.example.coinstudy.dataStore.MyDataStore
import com.example.coinstudy.network.repository.DBRepository
import com.example.coinstudy.network.repository.NetworkRepository
import com.example.umc.db.InterestCoinEntity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SelectViewModel : ViewModel() {

    private val networkRepository = NetworkRepository()
    private val dbRepository = DBRepository()

    private lateinit var currentPriceResultList : ArrayList<CurrentPriceResult>

    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResult : LiveData<List<CurrentPriceResult>>
        get() = _currentPriceResult

    private val _saved = MutableLiveData<String>()
    val saved : LiveData<String>
        get() = _saved

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

        _currentPriceResult.value = currentPriceResultList
    }

    fun setUpFirstFlag() = viewModelScope.launch {
        MyDataStore().setupFirstData()
    }

    /**
     * DB??? ????????? ??????
     * Dispatcher IO??? ?????? ????????? ?????? ???????????? I/O??? ??????????????? ?????????
     */
    fun saveSelectedCoinList(selectedCoinList: ArrayList<String>) = viewModelScope.launch(Dispatchers.IO) {

        // 1.?????? ?????? ????????? ???????????????
        for (coin in currentPriceResultList) {

            // 2.?????? ????????? ???????????? ??????
            //selectRVAdapter??? ????????? ?????? ???????????? ??????
            val selected = selectedCoinList.contains(coin.coinName)

            val interestCoinEntity = InterestCoinEntity(
                0,
                coin.coinName,
                coin.coinInfo.opening_price,
                coin.coinInfo.closing_price,
                coin.coinInfo.min_price,
                coin.coinInfo.max_price,
                coin.coinInfo.units_traded,
                coin.coinInfo.acc_trade_value,
                coin.coinInfo.prev_closing_price,
                coin.coinInfo.units_traded_24H,
                coin.coinInfo.acc_trade_value_24H,
                coin.coinInfo.fluctate_24H,
                coin.coinInfo.fluctate_rate_24H,
                selected
            )

            // 3.??????
            interestCoinEntity.let {
                dbRepository.insertInterestCoinData(it)
            }
        }

        /**
         * ??????????????? ????????? ?????? ??????
         * ????????? withContext ??????
         * */
        withContext(Dispatchers.Main) {
            _saved.value = "done"
        }
    }
}