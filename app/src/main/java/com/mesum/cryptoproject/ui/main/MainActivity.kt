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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.gray)

        val actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#1F2630"))
        actionBar?.setBackgroundDrawable(colorDrawable)

    }
}