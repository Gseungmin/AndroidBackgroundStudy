package com.example.coinstudy.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinstudy.network.repository.NetworkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SelectViewModel : ViewModel() {

    private val networkRepository = NetworkRepository()

    fun getCurrenctCoinList() = viewModelScope.launch {

        val result = networkRepository.getCurrentCoinList()

        Timber.d(result.toString())
    }

}