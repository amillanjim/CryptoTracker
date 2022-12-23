package com.alexm.cryptotracker.data.remote

import com.alexm.cryptotracker.data.remote.model.CoinDetailDto
import com.alexm.cryptotracker.data.remote.model.CoinDto
import com.alexm.cryptotracker.data.remote.model.TickersDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("v1/tickers")
    suspend fun getTickers(): List<TickersDto>

    @GET("v1/tickers/{coinId}")
    suspend fun getTickersById(
        @Path("coinId") coinId: String
    ): TickersDto

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(
        @Path("coinId") coinId: String
    ): CoinDetailDto
}