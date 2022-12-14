package com.example.coinstudy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.umc.db.InterestCoinDao
import com.example.umc.db.InterestCoinEntity

@Database(entities = [InterestCoinEntity::class], version = 2)
abstract class CoinPriceDataBase : RoomDatabase() {

    abstract fun interestCoinDao() : InterestCoinDao

    companion object {
        @Volatile
        private var INSTANCE : CoinPriceDataBase? = null

        fun getDatabase(
            context : Context
        ) : CoinPriceDataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDataBase::class.java,
                    "coin_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}