package com.mesum.cryptoproject.model.coin


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("close")
    val close: Double,
    @SerializedName("conversionSymbol")
    val conversionSymbol: String,
    @SerializedName("conversionType")
    val conversionType: String,
    @SerializedName("high")
    val high: Double,
    @SerializedName("low")
    val low: Double,
    @SerializedName("open")
    val `open`: Double,
    @SerializedName("time")
    val time: Int,
    @SerializedName("volumefrom")
    val volumefrom: Double,
    @SerializedName("volumeto")
    val volumeto: Double
)