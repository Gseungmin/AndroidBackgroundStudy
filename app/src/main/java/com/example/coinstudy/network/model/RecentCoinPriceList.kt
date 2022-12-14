package com.example.coinstudy.network.model

import com.example.coinstudy.dataModel.RecentPriceData

data class RecentCoinPriceList (

    val status: String,
    val data: List<RecentPriceData>
        )