package com.mesum.bitcoinapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mesum.cryptoproject.model.allcoinresponse.CryptoRp
import com.mesum.cryptoproject.model.coin.SingleCoin
import com.mesum.cryptoproject.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinViewModel : ViewModel(){

    val coinData = MutableLiveData<CryptoRp>()
    val coinList = MutableLiveData<SingleCoin>()

    fun pullData(){
        ApiClient.cryptoCompareApi.getDailyHistoricalData().enqueue(object  : retrofit2.Callback<CryptoRp>{
            override fun onResponse(call: Call<CryptoRp>, response: Response<CryptoRp>) {
                Log.d("UserData", response.body().toString())
                if (response.isSuccessful){
                    coinData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CryptoRp>, t: Throwable) {
                Log.d("UserData", t.toString())
            }

        })
    }


    fun getCoin(coin : String){
        ApiClient.cryptoCompareApi.getIndividualCrypto("https://min-api.cryptocompare.com/data/histoday?fsym=$coin&tsym=USD&limit=5").enqueue(object : Callback<SingleCoin>{
            override fun onResponse(call: Call<SingleCoin>, response: Response<SingleCoin>) {
                if (response.isSuccessful){
                    Log.d("singleCoinRp", response.body().toString())
                    coinList.value = response.body()

                }
            }

            override fun onFailure(call: Call<SingleCoin>, t: Throwable) {
                Log.d("singleCoinRp", t.message.toString())
            }

        })

    }

}