package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class USDDto (
  @SerializedName("price") val price: Double? = null,
  @SerializedName("volume_24h" ) val volume24h: Double? = null,
  @SerializedName("volume_24h_change_24h") val volume24hChange24h: Double? = null,
  @SerializedName("market_cap" ) val marketCap: Double? = null,
  @SerializedName("market_cap_change_24h") val marketCapChange24h: Double? = null,
  @SerializedName("percent_change_15m" ) val percentChange15m: Double? = null,
  @SerializedName("percent_change_30m" ) val percentChange30m: Double? = null,
  @SerializedName("percent_change_1h") val percentChange1h: Double? = null,
  @SerializedName("percent_change_6h") val percentChange6h: Double? = null,
  @SerializedName("percent_change_12h" ) val percentChange12h: Double? = null,
  @SerializedName("percent_change_24h" ) val percentChange24h: Double? = null,
  @SerializedName("percent_change_7d") val percentChange7d: Double? = null,
  @SerializedName("percent_change_30d" ) val percentChange30d: Double? = null,
  @SerializedName("percent_change_1y") val percentChange1y: Double? = null,
  @SerializedName("ath_price") val athPrice : Double? = null,
  @SerializedName("ath_date" ) val athDate: String? = null,
  @SerializedName("percent_from_price_ath" ) val percentFromPriceAth: Double? = null
): Parcelable