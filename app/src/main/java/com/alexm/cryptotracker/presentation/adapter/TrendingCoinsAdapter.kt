package com.alexm.cryptotracker.presentation.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.alexm.cryptotracker.R
import com.alexm.cryptotracker.base.BaseListAdapter
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.databinding.ItemTrendingCoinsBinding
import com.alexm.cryptotracker.domain.model.Tickers
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy

class TrendingCoinsAdapter(
    private val glide: RequestManager,
    private val context: Context,
    private val callback: (coinId: String?) -> Unit
): BaseListAdapter<Tickers, ItemTrendingCoinsBinding>(ItemTrendingCoinsBinding::inflate){

    override fun bindView(ticker: Tickers) {
        val profitValue = ticker.quotes?.USD?.percentChange30m ?: 0.0
        if (profitValue > 0.0) {
            binding.tvProfit.text =
                context.getString(R.string.percentage_change, profitValue.toString())
            binding.tvProfit
                .setTextColor(ContextCompat.getColor(context, R.color.green_lime))
        } else {
            binding.tvProfit.text = profitValue.toString()
            binding.tvProfit
                .setTextColor(ContextCompat.getColor(context, R.color.red_rose))
        }
        binding.apply {
            tvCoinName.text = ticker.symbol
            tvCoinFullName.text = ticker.name
            glide.load(Constants.GENERIC_COIN_IMAGE)
                .placeholder(R.drawable.generic_coin)
                .error(R.drawable.generic_coin)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivCoin)
        }
        binding.trendingContainer.setOnClickListener { callback(ticker.id) }
    }
}