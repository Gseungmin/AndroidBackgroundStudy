package com.example.umc.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "selected_coin_price_table")
data class SelectedCoinPriceEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coinName: String,
    val transaction_date: String,
    val type: String,
    val units_traded:String,
    val price: String,
    val total: String,
    val timeStamp : Date
)