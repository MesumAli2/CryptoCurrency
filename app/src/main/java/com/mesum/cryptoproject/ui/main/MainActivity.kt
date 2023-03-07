package com.mesum.cryptoproject.ui.main

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mesum.bitcoinapp.viewmodel.CoinViewModel
import com.mesum.cryptoproject.R
import com.mesum.cryptoproject.ui.detail.DetailActivity
import com.mesum.cryptoproject.ui.`interface`.OnCryptoClicked
import com.mesum.cryptoproject.ui.main.adapter.CoinListAdapter


class MainActivity : AppCompatActivity() {
    lateinit var viewModel : CoinViewModel
    val adapter : CoinListAdapter = CoinListAdapter(this, object : OnCryptoClicked{

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun onItemClick(cryptoName: String, cryptoFulName : String, cryptoPrice : String) {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("cryptoName", cryptoName)
            intent.putExtra("cryptoNameFull", cryptoFulName)
            intent.putExtra("cryptoPrice", cryptoPrice)

            startActivity(intent)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.gray)

        val actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#1F2630"))
        actionBar?.setBackgroundDrawable(colorDrawable)


        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.pullData()

        val rv : RecyclerView =  findViewById<RecyclerView>(R.id.coin_recycler)


        viewModel.coinData.observe(this){
            val list = it.data.filterNot {  it.coinInfo.name.contains("COVER")}
            Log.d("activity", "${it.data}")
            adapter.submitList(list)
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