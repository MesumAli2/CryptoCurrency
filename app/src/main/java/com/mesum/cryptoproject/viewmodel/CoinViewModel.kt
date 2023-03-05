package com.mesum.bitcoinapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesum.cryptoproject.model.dataModel
import com.mesum.cryptoproject.network.ApiClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CoinViewModel : ViewModel(){

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


        ApiClient.cryptoCompareApi.getDailyHistoricalData().enqueue(object  : retrofit2.Callback<dataModel>{
            override fun onResponse(call: Call<dataModel>, response: Response<dataModel>) {
                                    Log.d("UserData", response.body().toString())

            }

            override fun onFailure(call: Call<dataModel>, t: Throwable) {
                Log.d("UserData", t.toString())
            }

        })




    }

}