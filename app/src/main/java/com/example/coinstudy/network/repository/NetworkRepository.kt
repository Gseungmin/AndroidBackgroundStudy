package com.example.coinstudy.network.repository

import com.example.coinstudy.network.Api
import com.example.coinstudy.network.RetrofitInstance

class NetworkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList();
}