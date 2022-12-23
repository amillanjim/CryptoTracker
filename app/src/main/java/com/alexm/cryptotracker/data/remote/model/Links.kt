package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Links(
    val explorer: List<String>?,
    val facebook: List<String>?,
    val reddit: List<String>?,
    val website: List<String>?,
    val youtube: List<String>?,
    @SerialName("source_code") val sourceCode: List<String>?
): Parcelable