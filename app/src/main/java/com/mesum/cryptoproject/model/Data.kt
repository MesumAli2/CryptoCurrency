package com.mesum.cryptoproject.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo,
    @SerializedName("DISPLAY")
    val dISPLAY: DISPLAY?,
    @SerializedName("RAW")
    val rAW: RAW?
)