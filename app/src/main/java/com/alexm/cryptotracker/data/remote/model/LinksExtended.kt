package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksExtended(
    val url: String,
    val type: String,
    val stats: Map<String, Int>?
): Parcelable