package tech.michalik.mvptesting

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("price_usd")
    val priceUsd: String
)