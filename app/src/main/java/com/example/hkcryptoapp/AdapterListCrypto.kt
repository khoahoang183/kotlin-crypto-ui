//        return ListCryptoHolder(
package com.example.hkcryptoapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hkcryptoapp.databinding.CellHompageCryptoBinding
import android.R.color

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat


class AdapterListCrypto : RecyclerView.Adapter<ListCryptoHolder>() {

    private lateinit var lstCrypto: ArrayList<Crypto>

    fun setData(lstCrypto: ArrayList<Crypto>) {
        this.lstCrypto = lstCrypto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCryptoHolder {
        return ListCryptoHolder(
            CellHompageCryptoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListCryptoHolder, position: Int) {
        if (holder is ListCryptoHolder) {
            holder.bindingData(lstCrypto[position])
        }
    }

    override fun getItemCount(): Int {
        return lstCrypto.size
    }
}

class ListCryptoHolder(var view: CellHompageCryptoBinding) : RecyclerView.ViewHolder(view.root) {
    @SuppressLint("SetTextI18n")
    fun bindingData(item: Crypto) {
        view.tvName.text = item.name
        view.tvVolume.text = "Vol ${item.volume} M "
        view.tvPrice1.text = "${item.price}"
        view.tvPrice2.text = "${item.price}"
        if (item.profit >= 0F) {
            view.tvProfit.text= "+${item.profit}%"
            view.tvProfit.background.setTint(ContextCompat.getColor(view.root.context,R.color.color_crypto_green))
        } else {
            view.tvProfit.text= "${item.profit}%"
            view.tvProfit.background.setTint(ContextCompat.getColor(view.root.context,R.color.color_crypto_red))
        }

    }
}