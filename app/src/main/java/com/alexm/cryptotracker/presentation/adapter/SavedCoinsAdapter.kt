package com.alexm.cryptotracker.presentation.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.alexm.cryptotracker.R
import com.alexm.cryptotracker.base.BaseListAdapter
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.databinding.ItemMyCoinsBinding
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy

class SavedCoinsAdapter(
    private val glide: RequestManager,
    private val context: Context,
    private val callback: (coinId: String?) -> Unit
): BaseListAdapter<CoinEntity, ItemMyCoinsBinding>(ItemMyCoinsBinding::inflate){

    override fun bindView(savedCoin: CoinEntity) {
        binding.apply {
            tvCoinName.text = savedCoin.symbol
            tvCoinFullName.text = savedCoin.name
            tvPrice.text = savedCoin.price.toString()
            tvProfit.text = savedCoin.percentChange30m.toString()
            glide.load(savedCoin.logo)
                .placeholder(R.drawable.generic_coin)
                .error(R.drawable.generic_coin)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivCoin)
            val profitValue = savedCoin.percentChange30m ?: 0.0
            if (profitValue > 0.0) {
                binding.tvProfit.text = "^ $profitValue"
                binding.tvProfit
                    .setTextColor(ContextCompat.getColor(context, R.color.green_lime))
            } else {
                binding.tvProfit.text = profitValue.toString()
                binding.tvProfit
                    .setTextColor(ContextCompat.getColor(context, R.color.red_rose))
            }
            binding.clSavedCoin.setOnClickListener { callback(savedCoin.coinId) }
        }
    }
}