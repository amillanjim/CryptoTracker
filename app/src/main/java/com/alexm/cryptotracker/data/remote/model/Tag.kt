package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Tag(
    val id: String,
    val name: String,
    val description: String? = "",
    val type: String? = "",
    @SerialName("coin_counter") val coinCounter: Int,
    @SerialName("ico_counter") val icoCounter: Int,
    val coins: List<String>? = emptyList()
): Parcelable