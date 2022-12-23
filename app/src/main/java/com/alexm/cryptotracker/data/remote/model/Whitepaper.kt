package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Whitepaper(
    val link: String,
    val thumbnail: String
): Parcelable