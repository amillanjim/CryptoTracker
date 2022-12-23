package com.alexm.cryptotracker.data.repository

import com.alexm.cryptotracker.data.local.CoinDao
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.data.remote.CoinPaprikaApi
import com.alexm.cryptotracker.data.remote.model.CoinDetailDto
import com.alexm.cryptotracker.data.remote.model.CoinDto
import com.alexm.cryptotracker.data.remote.model.TickersDto
import com.alexm.cryptotracker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private val coinDao: CoinDao
): CoinRepository {
    override suspend fun getTickers(): List<TickersDto> = api.getTickers()

    override suspend fun getTickerById(coinId: String): TickersDto =
        api.getTickersById(coinId = coinId)

    override suspend fun getCoins(): List<CoinDto> = api.getCoins()

    override suspend fun getCoinById(coinId: String): CoinDetailDto =
        api.getCoinById(coinId)

    override suspend fun saveCoin(coin: CoinEntity) = coinDao.insertCoin(coin)

    override suspend fun deleteCoin(coin: CoinEntity) = coinDao.deleteCoin(coin)

    override suspend fun observeAllSavedCoins(): List<CoinEntity>  =
        coinDao.observeAllSavedCoins()

    override suspend fun observeCoinByName(coinName: String): CoinEntity =
        coinDao.observeCoinByName(coinName = coinName)
}