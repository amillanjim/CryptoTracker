package com.alexm.cryptotracker.domain.repository

import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.data.remote.model.CoinDetailDto
import com.alexm.cryptotracker.data.remote.model.CoinDto
import com.alexm.cryptotracker.data.remote.model.TickersDto

interface CoinRepository {
    suspend fun getTickers(): List<TickersDto>

    suspend fun getTickerById(coinId: String): TickersDto

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

    suspend fun saveCoin(coin: CoinEntity)

    suspend fun deleteCoin(coin: CoinEntity?)

    suspend fun observeAllSavedCoins(): List<CoinEntity>

    suspend fun observeCoinByName(coinId: String): CoinEntity
}