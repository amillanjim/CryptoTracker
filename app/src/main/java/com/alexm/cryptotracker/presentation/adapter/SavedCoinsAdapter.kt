package com.alexm.cryptotracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexm.cryptotracker.common.DiffUtilCallback
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.databinding.ItemMyCoinsBinding
import javax.inject.Inject

class SavedCoinsAdapter @Inject constructor():
    ListAdapter<CoinEntity, SavedCoinsAdapter.SavedCoinsViewHolder>(DiffUtilCallback()){

    private var _binding: ItemMyCoinsBinding? = null
    private val binding get() = _binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedCoinsViewHolder {
        _binding = ItemMyCoinsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false )
        return SavedCoinsViewHolder(itemView = _binding!!)
    }

    override fun onBindViewHolder(holder: SavedCoinsViewHolder, position: Int) =
        holder.bind(savedCoin = getItem(position))

    inner class SavedCoinsViewHolder(val itemView: ItemMyCoinsBinding): RecyclerView.ViewHolder(
        itemView.root
    ){
        internal fun bind(savedCoin: CoinEntity) {
            binding?.apply {
                tvCoinName.text = savedCoin.symbol
                tvCoinFullName.text = savedCoin.name
                tvCoins.text = savedCoin.coinsAmount
                tvProfit.text = savedCoin.rank.toString()
            }
        }
    }
}