package com.alexm.cryptotracker.domain.model

import com.alexm.cryptotracker.data.remote.model.Quotes

data class Tickers(
    val id: String?,
    val name: String?,
    val rank: Int?,
    val symbol: String?,
    val quotes: Quotes?
)