package com.example.hkcryptoapp.detailcryptoscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hkcryptoapp.data.Crypto
import com.example.hkcryptoapp.databinding.ActivityDetailBinding

class DetailCryptoScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var itemData: Crypto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemData = intent.getSerializableExtra("data") as Crypto
        initData()

        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun initData() {
        binding.tvName.text = itemData.name
    }
}