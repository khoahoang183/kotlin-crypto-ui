package com.example.hkcryptoapp.mainscreen

import android.annotation.SuppressLint
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

@SuppressLint("NotifyDataSetChanged")
class MainScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lstData: ArrayList<Crypto>
    private var adapterList: AdapterListCrypto? = null
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        initFakeTimerAPI()
    }

    private fun initFakeTimerAPI() {
        timer?.cancel()
        timerTask?.cancel()

        timer = Timer()
        timerTask = timerTask {
            for (item in lstData) {
                val minDiff: Float = item.price * -0.005F
                val maxDiff: Float = item.price * 0.005F

                val priceDiff: Float = (minDiff + Math.random() * (maxDiff - minDiff)).toFloat()
                item.profit = (priceDiff / item.price) * 100
                item.price += priceDiff
            }
            runOnUiThread {
                adapterList?.notifyDataSetChanged()
            }
        }
        timer?.schedule(timerTask, 1000, 2000)
    }

    private fun initView() {
        binding.rvListcrypto.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.tvTitle1.setOnClickListener {
            lstData.sortBy { list -> list.name }
            adapterList?.notifyDataSetChanged()
        }

        binding.tvTitle2.setOnClickListener {
            lstData.sortBy { list -> list.price }
            adapterList?.notifyDataSetChanged()
        }

        binding.tvTitle3.setOnClickListener {
            lstData.sortBy { list -> list.profit }
            adapterList?.notifyDataSetChanged()
        }

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = false
            initData()
            initFakeTimerAPI()
        }

    }

    private fun initData() {
        lstData = ArrayList()
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

        if (adapterList == null)
            adapterList = AdapterListCrypto()

        adapterList?.setData(lstData) {
            val intent = Intent(this, DetailCryptoScreenActivity::class.java)
            intent.putExtra("data", it)
            this.startActivity(intent)
        }
        binding.rvListcrypto.adapter = adapterList
    }
}