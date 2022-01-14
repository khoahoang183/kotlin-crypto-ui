//        return ListCryptoHolder(
package com.example.hkcryptoapp.mainscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hkcryptoapp.databinding.CellHompageCryptoBinding

import androidx.core.content.ContextCompat
import com.example.hkcryptoapp.data.Crypto
import com.example.hkcryptoapp.R


class AdapterListCrypto : RecyclerView.Adapter<ListCryptoHolder>() {

    private lateinit var lstCrypto: ArrayList<Crypto>
    private lateinit var onItemClick : (item : Crypto) ->Unit

    fun setData(lstCrypto: ArrayList<Crypto>, onItemClick : (item : Crypto) ->Unit) {
        this.lstCrypto = lstCrypto
        this.onItemClick=onItemClick
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
            holder.bindingData(lstCrypto[position],onItemClick)
        }
    }

    override fun getItemCount(): Int {
        return lstCrypto.size
    }
}

class ListCryptoHolder(var view: CellHompageCryptoBinding) : RecyclerView.ViewHolder(view.root) {
    @SuppressLint("SetTextI18n")
    fun bindingData(item: Crypto, onItemClick : (item : Crypto) ->Unit) {
        itemView.setOnClickListener {
            onItemClick.invoke(item)
        }
        view.tvName.text = item.name
        view.tvVolume.text = "Vol ${item.volume} M "
        view.tvPrice1.text = if (item.price >=0) String.format("%.2f", item.price) else "0.00"
        view.tvPrice2.text = if (item.price >=0) String.format("%.5f", item.price) else "0.00"

        view.tvProfit.text = String.format("%.2f", item.profit) + "%"
        if (item.profit > 0F) {
            view.tvProfit.background.setTint(
                ContextCompat.getColor(
                    view.root.context,
                    R.color.color_crypto_green
                )
            )
        } else if (item.profit == 0F) {
            view.tvProfit.background.setTint(
                ContextCompat.getColor(
                    view.root.context,
                    R.color.color_crypto_grey
                )
            )
        } else {
            view.tvProfit.background.setTint(
                ContextCompat.getColor(
                    view.root.context,
                    R.color.color_crypto_red
                )
            )
        }

    }
}