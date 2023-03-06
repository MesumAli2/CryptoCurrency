package com.mesum.bitcoinapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mesum.cryptoproject.model.CryptoRp
import com.mesum.cryptoproject.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class CoinViewModel : ViewModel(){

    val coinData = MutableLiveData<CryptoRp>()

    fun pullData(){
//        viewModelScope.launch() {
//            try {
//                val response = ApiClient.cryptoCompareApi.getDailyHistoricalData("BTC", "USD", 30)
//                Log.d("UserData", response.toString())
////                if (response.isSuccessful) {
////                    val data = response.body()
////                    // Process the response data
////
////                    Log.d("UserData", response.body().toString())
////                } else {
////                    // Handle API error
////
////                }
//            }catch (e :Exception){
//                Log.d("UserData", "Api Error ${e.message}")
//            }
//
//        }

        Log.d("tag", "tag")
        Log.d("tag", "tag")

        Log.d("tag1", "tag1")

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

}