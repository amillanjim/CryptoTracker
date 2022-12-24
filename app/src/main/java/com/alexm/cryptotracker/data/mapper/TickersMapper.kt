package com.alexm.cryptotracker.data.mapper

import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.data.remote.model.TickersDto
import com.alexm.cryptotracker.domain.model.Tickers

fun TickersDto.toTickers(): Tickers =
    Tickers(
        id = this.id,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol,
        quotes = this.quotes
    )


fun Tickers.toCoin(logo: String): CoinEntity =
    CoinEntity(
        coinId = this.id,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol,
        logo = logo,
        price = this.quotes?.USD?.price,
        percentChange30m = this.quotes?.USD?.percentChange30m
    )

