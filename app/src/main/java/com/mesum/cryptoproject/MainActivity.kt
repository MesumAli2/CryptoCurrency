package com.mesum.cryptoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mesum.bitcoinapp.viewmodel.CoinViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel : CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        viewModel.pullData()

        Log.d("Test", "Test Log")

        Log.d("Test", "second test third commit")

        println("Change 2 ")

        print("Crypto console merge commit")

    }
}