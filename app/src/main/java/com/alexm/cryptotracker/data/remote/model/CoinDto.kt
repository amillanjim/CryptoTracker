package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class CoinDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("rank") val rank: Int,
    @SerialName("symbol") val symbol: String,
    @SerialName("type") val type: String,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("is_new") val isNew: Boolean,
): Parcelable