package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TickersDto (
  @SerializedName("id") var id: String? = null,
  @SerializedName("name") var name: String? = null,
  @SerializedName("symbol") var symbol: String? = null,
  @SerializedName("rank") var rank: Int? = null,
  @SerializedName("circulating_supply") var circulatingSupply: Double? = null,
  @SerializedName("total_supply") var totalSupply: Double? = null,
  @SerializedName("max_supply") var maxSupply: Double? = null,
  @SerializedName("beta_value") var betaValue: Double? = null,
  @SerializedName("first_data_at") var firstDataAt: String? = null,
  @SerializedName("last_updated") var lastUpdated: String? = null,
  @SerializedName("quotes") var quotes: Quotes?
): Parcelable