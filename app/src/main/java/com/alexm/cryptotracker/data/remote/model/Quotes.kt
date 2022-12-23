package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quotes (
  @SerializedName("USD") val USD: USDDto?
): Parcelable