package com.example.coinstudy.network.repository

import com.example.coinstudy.App
import com.example.coinstudy.db.CoinPriceDataBase
import com.example.coinstudy.network.Api
import com.example.coinstudy.network.RetrofitInstance
import com.example.umc.db.InterestCoinEntity

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
}