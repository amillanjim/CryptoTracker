package com.alexm.cryptotracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "coins")
data class CoinEntity @Inject constructor(
    @PrimaryKey(autoGenerate = false)
    val coinId: String,
    val name: String?,
    val rank: Int?,
    val symbol: String?,
    val logo: String?,
    val price: Double?,
    val percentChange30m: Double?
    )