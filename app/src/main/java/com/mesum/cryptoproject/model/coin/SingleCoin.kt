package com.mesum.cryptoproject.model.coin


import com.google.gson.annotations.SerializedName

data class SingleCoin(
    @SerializedName("Aggregated")
    val aggregated: Boolean,
    @SerializedName("ConversionType")
    val conversionType: ConversionType,
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("FirstValueInArray")
    val firstValueInArray: Boolean,
    @SerializedName("HasWarning")
    val hasWarning: Boolean,
    @SerializedName("RateLimit")
    val rateLimit: RateLimit,
    @SerializedName("Response")
    val response: String,
    @SerializedName("TimeFrom")
    val timeFrom: Int,
    @SerializedName("TimeTo")
    val timeTo: Int,
    @SerializedName("Type")
    val type: Int
)