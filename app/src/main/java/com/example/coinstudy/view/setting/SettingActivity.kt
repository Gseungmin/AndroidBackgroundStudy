package com.example.coinstudy.view.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.coinstudy.R
import com.example.coinstudy.databinding.ActivityMainBinding
import com.example.coinstudy.databinding.ActivitySettingBinding
import com.example.coinstudy.network.service.PriceForegroundService

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /**ForeGround Service 시작*/
        binding.startForeground.setOnClickListener {
            val intent = Intent(this, PriceForegroundService::class.java)
            intent.action = "START"
            startService(intent)
        }

        /**ForeGround Service 중단*/
        binding.stopForeground.setOnClickListener {
            val intent = Intent(this, PriceForegroundService::class.java)
            intent.action = "STOP"
            startService(intent)
        }
    }
}