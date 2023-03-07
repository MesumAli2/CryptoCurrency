package com.mesum.cryptoproject.ui.main.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mesum.cryptoproject.R
import com.mesum.cryptoproject.model.allcoinresponse.Data
import com.mesum.cryptoproject.ui.`interface`.OnCryptoClicked


class CoinListAdapter(val ctx: Context,val  param: OnCryptoClicked) : ListAdapter<Data, CoinListAdapter.RvViewHolder>(diffUtil) {
    inner class RvViewHolder (view : View): RecyclerView.ViewHolder(view ){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rvcrypto_item, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
       val coinImageView = holder.itemView.findViewById<ImageView>(R.id.coin_logo)


        val coin = getItem(position)
        val imageUrl = "https://www.cryptocompare.com${coin.coinInfo.imageUrl}"
        Glide.with(ctx)
            .load(imageUrl)
            .into(coinImageView)



        holder.itemView.findViewById<TextView>(R.id.coin_name).text = coin.coinInfo.name
        holder.itemView.findViewById<TextView>(R.id.coin_price).text = coin?.dISPLAY?.USD?.pRICE.toString()
        holder.itemView.findViewById<TextView>(R.id.coin_full_nam).text = coin.coinInfo.fullName.toString()
        holder.itemView.setOnClickListener {
            param.onItemClick(coin.coinInfo.name, coin.coinInfo.fullName, coin.dISPLAY?.USD?.pRICE.toString())

        }

    }
}

val diffUtil = object  : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return newItem.coinInfo.name == oldItem.coinInfo.name
    }

}
