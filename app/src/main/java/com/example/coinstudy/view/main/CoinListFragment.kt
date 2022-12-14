package com.example.coinstudy.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.coinstudy.R
import com.example.coinstudy.databinding.FragmentCoinListBinding
import com.example.coinstudy.databinding.FragmentIntro1Binding
import com.example.umc.db.InterestCoinEntity
import timber.log.Timber

class CoinListFragment : Fragment() {

    private var _binding : FragmentCoinListBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels()

    private val selectedList = ArrayList<InterestCoinEntity>()
    private val unSelectedList = ArrayList<InterestCoinEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllInterestCoinData()
        viewModel.selectedCoinList.observe(viewLifecycleOwner, Observer{

            selectedList.clear()
            unSelectedList.clear()

            for (item in it) {
                if (item.selected) {
                    selectedList.add(item)
                } else {
                    unSelectedList.add(item)
                }
            }
        })
    }

    //viewBinding이 더이상 필요 없을 경우 null 처리 필요
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}