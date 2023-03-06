package com.mesum.cryptoproject.`interface`

import com.mesum.cryptoproject.model.CryptoRp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCompareApi {



    @GET("mktcapfull")
     fun getDailyHistoricalData(
        @Query("tsym") toSymbol: String = "USD",
        @Query("limit") limit: Int = 100
    ): Call<CryptoRp>



}
