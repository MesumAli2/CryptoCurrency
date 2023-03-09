package com.mesum.cryptoproject.network

import android.util.Log
import com.mesum.cryptoproject.`interface`.CryptoCompareApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    val httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
    fun createClient(): OkHttpClient {
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Apikey", "164226d1dc96912fa9b3780662fbb2c2ef441c0a29e72fbbdad7b98644e40ddf")
                .build()
            chain.proceed(request)
        }
        return httpClient.build()

        Log.d("rd", "rd")
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://min-api.cryptocompare.com/data/top/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(createClient())
        .build()


    object ApiClient{
        val cryptoCompareApi: CryptoCompareApi = retrofit.create(CryptoCompareApi::class.java)
    }
