package com.mesum.cryptoproject.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mesum.bitcoinapp.viewmodel.CoinViewModel
import com.mesum.cryptoproject.R
import com.mesum.cryptoproject.ui.adapter.CoinListAdapter

class MainActivity : AppCompatActivity() {
    lateinit var viewModel : CoinViewModel
    val adapter : CoinListAdapter = CoinListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.pullData()

        val rv : RecyclerView =  findViewById<RecyclerView>(R.id.coin_recycler)


        viewModel.coinData.observe(this){
            Log.d("activity", "${it.data}")
            adapter.submitList(it.data)
            rv.adapter = adapter
        }

        Log.d("Test", "Test Log")

        Log.d("Test", "second test third commit")

        println("Change 2 ")


        println("cryptogeniussatoshi")
        print("Crypto console merge commit")

        println("Hello from real friend ")

        println("I will creater a merge confewfewfweqfqw hfjhfhjfjhf elict nakamto")
  

    }
}