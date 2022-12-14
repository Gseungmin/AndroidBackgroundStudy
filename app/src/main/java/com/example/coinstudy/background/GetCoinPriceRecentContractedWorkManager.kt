package com.example.coinstudy.background

import android.content.Context
import android.content.ContextParams
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coinstudy.network.model.RecentCoinPriceList
import com.example.coinstudy.network.repository.DBRepository
import com.example.coinstudy.network.repository.NetworkRepository
import com.example.umc.db.SelectedCoinPriceEntity
import timber.log.Timber
import java.lang.reflect.Parameter
import java.sql.Timestamp
import java.util.Calendar
import java.util.Date

/**
 * 최근 거래된 코인 내역을 가져오는 워크 매니저
 * 3.DB에 가격 변동 정보를 저장
 * */
class GetCoinPriceRecentContractedWorkManager(val context: Context, workerParameters: WorkerParameters)
    : CoroutineWorker(context, workerParameters){

    private val dbRepository = DBRepository()
    private val networkRepository = NetworkRepository()

    override suspend fun doWork(): Result {

        Timber.d("doWork")

        getAllInterestSelectedCoinData()

        return Result.success()
    }

    /**
     *  1.관심있어하는 코인 리스트를 가지고옴
     * */
    suspend fun getAllInterestSelectedCoinData() {

        val selectedCoinList = dbRepository.getAllInterestSelectedCoinData()

        val timeStamp = Calendar.getInstance().time


        for (coinData in selectedCoinList) {

            /**
             * 2.관심있는 코인 각각의 가격 변동 정보를 가지고옴 (New API)
             * */
            val recentCoinPriceList = networkRepository.getRecentCoinPrice(coinData.coin_name)

            Timber.d(recentCoinPriceList.toString())

            saveSelectedCoinPrice(
                coinData.coin_name,
                recentCoinPriceList,
                timeStamp
            )
        }
    }

    fun saveSelectedCoinPrice(
        coinName: String,
        recentCoinPriceList: RecentCoinPriceList,
        timeStamp: Date
    ) {
        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            0,
            coinName,
            recentCoinPriceList.data[0].transaction_date,
            recentCoinPriceList.data[0].type,
            recentCoinPriceList.data[0].units_traded,
            recentCoinPriceList.data[0].price,
            recentCoinPriceList.data[0].total,
            timeStamp
        )

        dbRepository.insertCoinPriceData(selectedCoinPriceEntity)
    }
}