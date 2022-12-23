package com.alexm.cryptotracker.data.mapper

import com.alexm.cryptotracker.data.remote.model.CoinDto
import com.alexm.cryptotracker.domain.model.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = this.id,
        isActive = this.isActive,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol
    )
}