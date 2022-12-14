package com.example.coinstudy.view.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import com.example.coinstudy.MainActivity
import com.example.coinstudy.databinding.ActivityIntroBinding
import com.example.coinstudy.databinding.ActivityMainBinding
import timber.log.Timber

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private val viewModel : IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Timber.d("onCreate")

        viewModel.checkFirstFlag()

        viewModel.first.observe(this, {
            if (it) {
                //처음 접속 유저가 아님
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                //처음 접속 유저
                binding.animationView.visibility = View.INVISIBLE
                binding.fragmentContainerView.visibility = View.VISIBLE
            }
        })
    }
}