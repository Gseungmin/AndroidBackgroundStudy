package com.example.coinstudy.background

import android.content.Context
import android.content.ContextParams
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coinstudy.network.repository.DBRepository
import com.example.coinstudy.network.repository.NetworkRepository
import timber.log.Timber
import java.lang.reflect.Parameter

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

        for (coinData in selectedCoinList) {

            /**
             * 2.관심있는 코인 각각의 가격 변동 정보를 가지고옴 (New API)
             * */
            val recentCoinPrice = networkRepository.getRecentCoinPrice(coinData.coin_name)

            Timber.d(recentCoinPrice.toString())
        }
    }
    }