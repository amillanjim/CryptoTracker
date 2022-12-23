package com.alexm.cryptotracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexm.cryptotracker.data.remote.model.TeamMember
import javax.inject.Inject

@Entity(tableName = "coins")
data class CoinEntity @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val coinId: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val logo: String,
    val coinsAmount: String = "$7,324.00"
    )