package com.alexm.cryptotracker.data.mapper

import com.alexm.cryptotracker.data.remote.model.TickersDto
import com.alexm.cryptotracker.domain.model.Tickers

fun TickersDto.toTickers(): Tickers {
    return Tickers(
        id = this.id,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol,
        quotes = this.quotes
    )
}
