package com.example.coinstudy.view

import android.content.Intent
import android.content.PeriodicSync
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.coinstudy.MainActivity
import com.example.coinstudy.background.GetCoinPriceRecentContractedWorkManager
import com.example.coinstudy.databinding.ActivitySelectBinding
import com.example.coinstudy.view.adapter.SelectRVAdapter
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding
    private val viewModel : SelectViewModel by viewModels()

    private lateinit var selectRVAdapter: SelectRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this, {

            selectRVAdapter = SelectRVAdapter(this, it)

            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

            Timber.d(it.toString())
        })

        binding.laterTextArea.setOnClickListener {

            viewModel.setUpFirstFlag()
            viewModel.saveSelectedCoinList(selectRVAdapter.selectedCoinList)

        }

        viewModel.saved.observe(this, Observer {
            if (it.equals("done")) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                saveInterestCoinDataPeriodic()
            }
        })
    }

    private fun saveInterestCoinDataPeriodic() {

        //15분마다 우리가 설정한 워크 매니저를 돌릴것을 선언
        val myWork = PeriodicWorkRequest.Builder(
            GetCoinPriceRecentContractedWorkManager::class.java,
            15, TimeUnit.MINUTES
        ).build()

        //myWork를 실행, unique하게 한개만 돌아가게 설정하는 것, 여러개의 워크 매니저가 돌아가는 것 방지
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "GetCoinPriceRecentContractedWorkManager",
            ExistingPeriodicWorkPolicy.KEEP,
            myWork
        )
    }
}