package com.example.umc.db

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_coin_price_table")
data class SelectedCoinPriceEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "location")
    var location: String,
    @ColumnInfo(name = "image")
    var image: Bitmap,
    @ColumnInfo(name = "date")
    var date: String
)