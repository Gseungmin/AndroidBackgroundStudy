package com.example.coinstudy.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinstudy.R
import com.example.coinstudy.databinding.FragmentCoinListBinding
import com.example.coinstudy.databinding.FragmentPriceChangeBinding
import com.example.coinstudy.view.adapter.PriceListUpDownRVAdapter
import timber.log.Timber

class PriceChangeFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentPriceChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPriceChangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllSelectedCoinData()

        viewModel.arr15min.observe(viewLifecycleOwner, Observer {

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(), it)
            binding.price15m.adapter = priceListUpDownRVAdapter
            binding.price15m.layoutManager = LinearLayoutManager(requireContext())

        })

        viewModel.arr30min.observe(viewLifecycleOwner, Observer {

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(), it)
            binding.price30m.adapter = priceListUpDownRVAdapter
            binding.price30m.layoutManager = LinearLayoutManager(requireContext())

        })

        viewModel.arr45min.observe(viewLifecycleOwner, Observer {

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(), it)
            binding.price45m.adapter = priceListUpDownRVAdapter
            binding.price45m.layoutManager = LinearLayoutManager(requireContext())
        })
    }

    //viewBinding이 더이상 필요 없을 경우 null 처리 필요
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}