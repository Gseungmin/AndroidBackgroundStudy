package com.example.coinstudy.network.repository

import com.example.coinstudy.App
import com.example.coinstudy.db.CoinPriceDataBase
import com.example.coinstudy.network.Api
import com.example.coinstudy.network.RetrofitInstance
import com.example.umc.db.InterestCoinEntity
import com.example.umc.db.SelectedCoinPriceEntity

class DBRepository {

    val context = App.context()
    val db = CoinPriceDataBase.getDatabase(context)

    // InterestCoin
    fun getAllInterestCoinData() = db.interestCoinDao().getAllData()

    fun insertInterestCoinData(interestCoinEntity: InterestCoinEntity) =
        db.interestCoinDao().insert(interestCoinEntity)

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) =
        db.interestCoinDao().update(interestCoinEntity)

    fun getAllInterestSelectedCoinData() = db.interestCoinDao().getSelectedCoinList()

    //RecentPrice
    fun getAllCoinPriceData() = db.selectedCoinPriceDao().getAllData()

    fun insertCoinPriceData(selectedCoinPriceEntity: SelectedCoinPriceEntity) =
        db.selectedCoinPriceDao().insert(selectedCoinPriceEntity)

    fun getOneSelectedCoinData(coinName : String) = db.selectedCoinPriceDao().getOneCoinData(coinName)
}