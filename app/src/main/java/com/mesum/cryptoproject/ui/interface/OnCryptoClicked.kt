package com.mesum.cryptoproject.ui.`interface`

import androidx.annotation.RequiresApi

interface OnCryptoClicked {

    @RequiresApi(value = 33)
    fun onItemClick(cryptoName: String, cryptoFulName: String, cryptoPrice: String)
}