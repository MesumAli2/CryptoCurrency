package com.mesum.cryptoproject.model.allcoinresponse


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("Weiss")
    val weiss: Weiss
)