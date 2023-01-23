package com.alexm.cryptotracker.domain.repository

import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.data.remote.model.CoinDetailDto
import com.alexm.cryptotracker.data.remote.model.CoinDto
import com.alexm.cryptotracker.data.remote.model.TickersDto
import com.alexm.cryptotracker.domain.model.Tickers

class FakeCoinRepository: CoinRepository {

    private val tickers = mutableListOf<Tickers>()

    override suspend fun getTickers(): List<TickersDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getTickerById(coinId: String): TickersDto {
        TODO("Not yet implemented")
    }

    override suspend fun getCoins(): List<CoinDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        TODO("Not yet implemented")
    }

    override suspend fun saveCoin(coin: CoinEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCoin(coin: CoinEntity?) {
        TODO("Not yet implemented")
    }

    override suspend fun observeAllSavedCoins(): List<CoinEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun observeCoinByName(coinId: String): CoinEntity {
        TODO("Not yet implemented")
    }
}