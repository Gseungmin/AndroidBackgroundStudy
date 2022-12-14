package com.example.coinstudy.view.main

import androidx.lifecycle.*
import com.example.coinstudy.dataModel.CurrentPrice
import com.example.coinstudy.dataModel.CurrentPriceResult
import com.example.coinstudy.dataModel.UpDownDataSet
import com.example.coinstudy.dataStore.MyDataStore
import com.example.coinstudy.network.model.CurrentPriceList
import com.example.coinstudy.network.repository.DBRepository
import com.example.coinstudy.network.repository.NetworkRepository
import com.example.umc.db.InterestCoinEntity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val dbRepository = DBRepository()

    lateinit var selectedCoinList : LiveData<List<InterestCoinEntity>>

    private val _arr15min = MutableLiveData<List<UpDownDataSet>>()
    val arr15min : LiveData<List<UpDownDataSet>>
        get() = _arr15min
    private val _arr30min = MutableLiveData<List<UpDownDataSet>>()
    val arr30min : LiveData<List<UpDownDataSet>>
        get() = _arr30min
    private val _arr45min = MutableLiveData<List<UpDownDataSet>>()
    val arr45min : LiveData<List<UpDownDataSet>>
        get() = _arr45min

    //CoinListFragment
    fun getAllInterestCoinData() = viewModelScope.launch {

        val coinList = dbRepository.getAllInterestCoinData().asLiveData()
        selectedCoinList = coinList
    }

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            if (interestCoinEntity.selected) {
                interestCoinEntity.selected = false
            } else {
                interestCoinEntity.selected = true
            }
            dbRepository.updateInterestCoinData(interestCoinEntity)
        }


    //PriceChangeFragment
    fun getAllSelectedCoinData() = viewModelScope.launch(Dispatchers.IO) {

        //1. 관심있다고 선택한 코인 리스트 가져오기
        val selectedCoinData = dbRepository.getAllInterestSelectedCoinData()

        val arr15min = ArrayList<UpDownDataSet>()
        val arr30min = ArrayList<UpDownDataSet>()
        val arr45min = ArrayList<UpDownDataSet>()

        //2. 코인 하나씩 가져옴
        for (data in selectedCoinData) {

            val coinName = data.coin_name
            //3. 저장된 코인 가격 리스트 가져옴
            val oneCoinData = dbRepository.getOneSelectedCoinData(coinName)


            //4. 시간대마다 어떻게 변경되었는지 알려주는 로직 작성
            val size = oneCoinData.size

            //현재와 15분 전을 비교하려면 데이터가 2개는 필요
            if (size > 1) {
                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[1].price.toDouble()
                val upDownDataSet = UpDownDataSet(coinName, changedPrice.toString())
                arr15min.add(upDownDataSet)
            }

            //현재와 30분 전을 비교하려면 데이터가 2개는 필요
            if (size > 2) {
                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[2].price.toDouble()
                val upDownDataSet = UpDownDataSet(coinName, changedPrice.toString())
                arr30min.add(upDownDataSet)
            }

            //현재와 45분 전을 비교하려면 데이터가 2개는 필요
            if (size > 3) {
                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[3].price.toDouble()
                val upDownDataSet = UpDownDataSet(coinName, changedPrice.toString())
                arr45min.add(upDownDataSet)
            }
        }

        withContext(Dispatchers.Main) {
            _arr15min.value = arr15min
            _arr30min.value = arr30min
            _arr45min.value = arr45min
        }
    }
}