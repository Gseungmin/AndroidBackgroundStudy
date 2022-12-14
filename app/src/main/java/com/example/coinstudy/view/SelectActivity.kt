package com.example.coinstudy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.coinstudy.R
import com.example.coinstudy.databinding.ActivityIntroBinding
import com.example.coinstudy.databinding.ActivitySelectBinding
import timber.log.Timber

class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding
    private val viewModel : SelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getCurrentCoinList()
    }
}