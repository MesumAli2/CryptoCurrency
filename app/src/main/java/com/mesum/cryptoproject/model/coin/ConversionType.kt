package com.mesum.cryptoproject.model.coin


import com.google.gson.annotations.SerializedName

data class ConversionType(
    @SerializedName("conversionSymbol")
    val conversionSymbol: String,
    @SerializedName("type")
    val type: String
)