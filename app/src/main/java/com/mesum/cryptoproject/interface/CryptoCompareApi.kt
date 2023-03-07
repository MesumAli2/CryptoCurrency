package com.mesum.cryptoproject.`interface`

import com.mesum.cryptoproject.model.allcoinresponse.CryptoRp
import com.mesum.cryptoproject.model.coin.SingleCoin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface
CryptoCompareApi {



    @GET("mktcapfull")
     fun getDailyHistoricalData(
        @Query("tsym") toSymbol: String = "USD",
        @Query("limit") limit: Int = 100
    ): Call<CryptoRp>


     @GET
     fun getIndividualCrypto(@Url url : String)
     :Call<SingleCoin>



}
