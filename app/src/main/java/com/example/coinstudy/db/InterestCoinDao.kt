package com.example.umc.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCoinDao {

    /**
     * flow를 room과 같이 사용하는 것이 좋음
     * 데이터의 변경사항을 감지하기 좋음, flow
     * 즉 db에 데이터가 변경될때 변경 사항 감지하기 좋음
     * */
    @Query("SELECT * FROM interest_coin_table")
    fun getAllData() : Flow<List<InterestCoinEntity>>

    /**
     * 사용자가 선택한 데이터만 가지고 옴
     * */
    @Query("SELECT * FROM interest_coin_table WHERE selected = :selected")
    fun getSelectedCoinList(selected : Boolean = true) : List<InterestCoinEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(interestCoinEntity: InterestCoinEntity)

    @Update
    fun update(interestCoinEntity: InterestCoinEntity)
}