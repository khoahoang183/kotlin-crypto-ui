package com.example.hkcryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private lateinit var adapterList: AdapterListCrypto
    private lateinit var rvlistCrypto: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvlistCrypto = findViewById(R.id.rv_listcrypto)

        var lstData: ArrayList<Crypto> = ArrayList<Crypto>()
        lstData.add(Crypto("BTC", 940.5F, 41875.58F, 10, +0.53F))
        lstData.add(Crypto("MATIC", 139.22F, 2.122F, 5, -1.83F))
        lstData.add(Crypto("NANO", 1.63F, 2.983F, 5, -4.57F))
        lstData.add(Crypto("ADA", 125.18F, 1.17F, 10, +1.82F))
        lstData.add(Crypto("LTC", 40.18F, 130.4F, 10, -1.7F))

        adapterList = AdapterListCrypto()
        adapterList.setData(lstData)
        rvlistCrypto.adapter = adapterList
        rvlistCrypto.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        var timer = Timer()
        timer.schedule(timerTask {

            for (item in lstData) {
                val priceDiff = (-1..5).random()
                val rounded = String.format("%.2f", priceDiff.toFloat()/item.price *100)
                item.profit = rounded.toFloat()
                item.price += priceDiff
                if (item.price < 0)
                    item.price = 0F
            }
            runOnUiThread {
                adapterList.notifyDataSetChanged()
            }
        }, 2000, 2000)

    }
}