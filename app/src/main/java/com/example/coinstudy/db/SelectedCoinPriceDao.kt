package com.example.umc.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedCoinPriceDao {

    @Query("SELECT * FROM selected_coin_price_table")
    fun getAllData() : List<SelectedCoinPriceEntity>

    /**
     * 코인에 대해 저장된 정보 가지고 옴
     * BTC 15 30 45가 어떻게 변했는지 DB에 저장했던 값과 비교하는 용도
     * */
    @Query("SELECT * FROM selected_coin_price_table WHERE coinName = :coinName")
    fun getOneCoinData(coinName : String) : List<SelectedCoinPriceEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(selectedCoinPriceEntity: SelectedCoinPriceEntity)

    @Update
    fun update(selectedCoinPriceEntity: SelectedCoinPriceEntity)
}