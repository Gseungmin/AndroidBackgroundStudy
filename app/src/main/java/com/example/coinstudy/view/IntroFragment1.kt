package com.example.coinstudy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinstudy.R
import com.example.coinstudy.databinding.FragmentIntro1Binding

class IntroFragment1 : Fragment() {

    private var _binding : FragmentIntro1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding.root
    }

    //viewBinding이 더이상 필요 없을 경우 null 처리 필요
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}