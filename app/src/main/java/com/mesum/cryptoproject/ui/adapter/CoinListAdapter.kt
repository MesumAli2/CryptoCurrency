package com.mesum.cryptoproject.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mesum.cryptoproject.R
import com.mesum.cryptoproject.model.Data


class CoinListAdapter(val ctx : Context) : ListAdapter<Data, CoinListAdapter.RvViewHolder>(diffUtil) {
    inner class RvViewHolder (view : View): RecyclerView.ViewHolder(view ){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rvcrypto_item, parent, false))
    }

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
