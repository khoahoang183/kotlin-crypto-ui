package com.example.hkcryptoapp.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hkcryptoapp.data.Crypto
import com.example.hkcryptoapp.databinding.ActivityMainBinding
import com.example.hkcryptoapp.detailcryptoscreen.DetailCryptoScreenActivity
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

class MainScreenActivity : AppCompatActivity() {
    private lateinit var adapterList: AdapterListCrypto
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lstData: ArrayList<Crypto> = ArrayList()
        lstData.add(Crypto("BTC", 940.5F, 41875.58F, 10, +0.53F))
        lstData.add(Crypto("MATIC", 139.22F, 2.122F, 5, -1.83F))
        lstData.add(Crypto("NANO", 1.63F, 2.983F, 5, -4.57F))
        lstData.add(Crypto("ADA", 125.18F, 1.17F, 10, +1.82F))
        lstData.add(Crypto("LTC", 40.18F, 130.4F, 10, -1.7F))
        lstData.add(Crypto("XLM", 27.67F, 0.27F, 5, -4.57F))
        lstData.add(Crypto("DOGE", 424.01F, 0.1754F, 10, +1.82F))
        lstData.add(Crypto("BNB", 439.54F, 478.4F, 10, -1.7F))
        lstData.add(Crypto("DOGE", 424.01F, 0.1754F, 10, +1.82F))
        //lstData.add(Crypto("SHIB", 17.523F, 0.000002567F, 10, -1.7F))

        adapterList = AdapterListCrypto()
        adapterList.setData(lstData) {
            val intent = Intent(this, DetailCryptoScreenActivity::class.java)
            intent.putExtra("data", it)
            this.startActivity(intent)
        }
        binding.rvListcrypto.adapter = adapterList
        binding.rvListcrypto.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        Timer().schedule(timerTask {

            for (item in lstData) {
                val minDiff: Float = item.price * -0.005F
                val maxDiff: Float = item.price * 0.005F

                val priceDiff: Float = (minDiff + Math.random() * (maxDiff - minDiff)).toFloat()
                item.profit = (priceDiff / item.price) * 100
                item.price += priceDiff
            }
            runOnUiThread {
                adapterList.notifyDataSetChanged()
            }
        }, 1000, 2000)

    }
}