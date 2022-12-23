package com.alexm.cryptotracker.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexm.cryptotracker.R
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.common.DiffUtilCallback
import com.alexm.cryptotracker.databinding.ItemTrendingCoinsBinding
import com.alexm.cryptotracker.domain.model.Tickers
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TrendingCoinsAdapter @Inject constructor(
    private val glide: RequestManager,
    @ApplicationContext private val context: Context
): ListAdapter<Tickers, TrendingCoinsAdapter.TrendingCoinsViewHolder>(DiffUtilCallback()) {

    private var _binding: ItemTrendingCoinsBinding? = null
    private val binding get() = _binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingCoinsViewHolder {
        _binding = ItemTrendingCoinsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false )
        return TrendingCoinsViewHolder(itemView = _binding as ItemTrendingCoinsBinding)
    }

    override fun onBindViewHolder(holder: TrendingCoinsViewHolder, position: Int) =
        holder.bind(coin = getItem(position))

    inner class TrendingCoinsViewHolder(itemView: ItemTrendingCoinsBinding):
        RecyclerView.ViewHolder( itemView.root ){
        internal fun bind(coin: Tickers) {
            val profitValue = coin.quotes?.USD?.percentChange30m ?: 0.0
            if (profitValue > 0.0) {
                binding?.tvProfit?.text = "^ $profitValue"
                binding?.tvProfit
                    ?.setTextColor(ContextCompat.getColor(context, R.color.green_lime))
            } else {
                binding?.tvProfit?.text = profitValue.toString()
                binding?.tvProfit
                    ?.setTextColor(ContextCompat.getColor(context, R.color.red_rose))
            }
            binding?.apply {
                tvCoinName.text = coin.symbol
                tvCoinFullName.text = coin.name
                glide.load(Constants.GENERIC_COIN_IMAGE)
                    .placeholder(R.drawable.generic_coin)
                    .error(R.drawable.generic_coin)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivCoin)
            }
            binding?.trendingContainer?.setOnClickListener {}
        }
    }
}