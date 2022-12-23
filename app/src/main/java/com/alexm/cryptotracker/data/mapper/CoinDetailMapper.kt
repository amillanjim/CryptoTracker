package com.alexm.cryptotracker.data.mapper

import com.alexm.cryptotracker.data.remote.model.CoinDetailDto
import com.alexm.cryptotracker.domain.model.CoinDetail

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = this.id,
        name = this.name,
        description = this.description,
        symbol = this.symbol,
        rank = this.rank,
        isActive = this.isActive,
        logo = this.logo,
        tags = this.tags,
        team = this.team
    )
}