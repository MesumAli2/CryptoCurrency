package com.mesum.cryptoproject.`interface`

import com.mesum.cryptoproject.model.dataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCompareApi {
erg
    @GET("histoday")
     fun getDailyHistoricalData(
        @Query("fsym") fromSymbol: String = "BTC",
        @Query("tsym") toSymbol: String = "USD",
        @Query("limit") limit: Int = 30
    ): Call<dataModel>



}
