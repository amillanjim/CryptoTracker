package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Contract(
    @SerialName("contract") val contract: String,
    @SerialName("platform") val platform: String,
    @SerialName("type") val type: String
): Parcelable